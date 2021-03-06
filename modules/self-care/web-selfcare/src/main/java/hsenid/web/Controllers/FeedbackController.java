package hsenid.web.Controllers;

import hsenid.web.models.ServerResponseMessage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class FeedbackController {
    final static Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    @Value("${api.base.url}")
    private String baseUrl;

    @Value("${api.url.puchase.history.customer.items}")
    private String purchaseHistoryCustomerItemsUrl;

    @Value("${api.url.puchase.history.customer.items.count}")
    private String purchaseHistoryCustomerItemsCountUrl;

    @Value("${admin.url.base.image.item}")
    private String adminItemImageUrl;

    @Value("${api.url.feedback.get}")
    private String getFeedackUrl;

    @Value("${api.url.feedback.add}")
    private String addFeedackUrl;

    @Value("${api.url.feedback.update}")
    private String updateFeedackUrl;

    @RequestMapping(value = "/feedbacks", method = RequestMethod.GET)
    public ModelAndView loadFeedbacksPage() {
        ModelAndView modelAndView = new ModelAndView("includes/feedback");
        return modelAndView;
    }

    @RequestMapping(value = "/feedbacks/purchased-items", method = RequestMethod.GET)
    @ResponseBody
    public String getCustomerPurchasedItems(HttpSession session, HttpServletRequest request) {
        String url = baseUrl + purchaseHistoryCustomerItemsUrl + session.getAttribute("username") + "?page=" + "1" + "&records=" + "10";
        RestTemplate restTemplate = new RestTemplate();
        ServerResponseMessage responseMessage = restTemplate.getForObject(url, ServerResponseMessage.class);
        String htmlData = "";
        htmlData += "<div class='row' style='margin-top:15px;'>";
        for (int i = 0; i < responseMessage.getData().size(); i++) {
            JSONObject jsonObject = responseMessage.getData().get(i);
            if (i != 0 && i % 2 == 0) {
                htmlData += "</div>";
                htmlData += "<div class='row' style='margin-top:15px;'>";
            }
            htmlData += "<div class='col-md-6'>";
            htmlData += "<div style='float:left;'><img src='" + adminItemImageUrl + "/" + jsonObject.get("itemName").toString().replaceAll(" ", "") + ".jpg' width='150px' height='150px'></div>";
            htmlData += "<div style='float:left; margin-left:10px;'><h5 class='h5-itemname'>Item name: <span id='itemname-spn'>" + jsonObject.get("itemName") + "</span></h5>";
            htmlData += "<h5>Last order: " + jsonObject.get("lastOrdered") + "</h5>";
            htmlData += "<button class='btn-viewfeedback m-btn sm purple'>Feedback</button></div>";
            htmlData += "</div>";
        }
        return htmlData;
    }

    @RequestMapping(value = "/feedbacks/get", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray getFeedbackOfItem(HttpSession session, HttpServletRequest request) {
        String url = baseUrl + getFeedackUrl + request.getParameter("itemName") + "/" + session.getAttribute("username");
        RestTemplate restTemplate = new RestTemplate();
        ServerResponseMessage responseMessage = restTemplate.getForObject(url, ServerResponseMessage.class);
        JSONArray feedbackJson = new JSONArray();
        if (responseMessage.getData().size() > 0) {
            feedbackJson.add(responseMessage.getData().get(0));
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("comment", "");
            jsonObject.put("stars", "3");
            feedbackJson.add(jsonObject);
        }
        return feedbackJson;
    }

    @RequestMapping(value = "/feedbacks/add", method = RequestMethod.POST)
    @ResponseBody
    public int addFeedback(HttpSession session, HttpServletRequest request) {
        String url = baseUrl + addFeedackUrl;
        JSONObject feedbackJson = new JSONObject();

        feedbackJson.put("comment", request.getParameter("comment"));
        feedbackJson.put("numberOfStars", Integer.parseInt(request.getParameter("numberOfStars")));
        feedbackJson.put("customerUserName", session.getAttribute("username"));
        feedbackJson.put("itemName", request.getParameter("itemName"));

        RestTemplate restTemplate = new RestTemplate();
        ServerResponseMessage responseMessage = restTemplate.postForObject(url, feedbackJson, ServerResponseMessage.class);
        int statusCode = responseMessage.getCode();
        return statusCode;
    }

    @RequestMapping(value = "/feedbacks/update", method = RequestMethod.POST)
    @ResponseBody
    public int updateFeedback(HttpSession session, HttpServletRequest request) {
        String url = baseUrl + updateFeedackUrl;
        JSONObject feedbackJson = new JSONObject();

        feedbackJson.put("comment", request.getParameter("comment"));
        feedbackJson.put("numberOfStars", Integer.parseInt(request.getParameter("numberOfStars")));
        feedbackJson.put("customerUserName", session.getAttribute("username"));
        feedbackJson.put("itemName", request.getParameter("itemName"));

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(feedbackJson);
        ResponseEntity<ServerResponseMessage> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, ServerResponseMessage.class);

        return responseEntity.getBody().getCode();
    }
}