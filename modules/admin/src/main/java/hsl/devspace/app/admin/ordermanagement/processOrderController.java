package hsl.devspace.app.admin.ordermanagement;

import hsl.devspace.app.corelogic.repository.delivery.DeliveryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 1/10/17.
 */
@Controller
@RequestMapping(value = "/processOrders")
public class processOrderController {


    private static final Logger LOG = LogManager.getLogger(processOrderController.class);

    @Autowired
    private DeliveryRepository deliveryRepository;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView loadOrderView(ModelAndView modelview){

//        modelview.addObject("command", new Delivery());
        modelview.setViewName("/order_management/orderProcessView");
        return modelview;
    }

    //to view the order process table
    @RequestMapping(value = "/view/orderProcessTable", method = RequestMethod.GET)
    public
    @ResponseBody List<Map<String, Object>> viewDelivery(@RequestParam("initPage") String initPage,
                                                     @RequestParam("pgLimit") String  pgLimit) {

        LOG.error("inside the processOrder table load");
        //cast the initial page and page limits in pagination to integers
        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pgLimit);
        LOG.error("processOrder table  {} ,{}",limitPg,initPage);

        LOG.info("View all order details {} ", (deliveryRepository.selectDeliveryDetails(limitPg, initPg)));
        return deliveryRepository.selectDeliveryDetails(limitPg,initPg);
    }

    //to view the order process table
    @RequestMapping(value = "/delivery/count", method = RequestMethod.GET)
    public
    @ResponseBody int deliveryCount(){
        int count=deliveryRepository.countDeliveryDetails();
        return count;
    }

//    handler method to obtain the delivery agent list from the database based on the branch selected
    @RequestMapping(value = "/delivery/agents", method = RequestMethod.GET)
    public @ResponseBody List<String> agentListRetrieve(@RequestParam("selectedBranch") String selectedBranch){
        LOG.error("inside the agent list retrieve method");
        List<String> agentsList=deliveryRepository.selectDeliveryAgents(selectedBranch);
        LOG.error("the delivery agents for {} are {}",selectedBranch,agentsList);
        return agentsList;
    }

    //handler method to assign the delivery agent for a Pending order
    @RequestMapping(value = "/agentAssign", method = RequestMethod.GET)
    public @ResponseBody int agentAssign( @RequestParam("agentName") String agentName,
                                         @RequestParam("orderId") String orderId,
                                         HttpServletRequest request){

        //set the creator in the delivery assign using session attributes
        HttpSession session = request.getSession();
        String userId =(String)session.getAttribute("username");
        LOG.error("user: {}",userId);

        LOG.error("agent:{}",agentName);

        LOG.error("order:{}",orderId);

        int assignStatus=deliveryRepository.updateDelivery(agentName,userId,orderId);
        LOG.error("assignStatus:{}",assignStatus);

        return assignStatus;
    }

    //to view the order process table filtered by Delivery Status
    @RequestMapping(value = "/filter/byDeliverStatus", method = RequestMethod.GET)
    public
    @ResponseBody List<Map<String, Object>> filterDeliveryByStatus(@RequestParam("searchDelivery ") String searchDelivery ,
                                                                   @RequestParam("initPage") String initPage,
                                                                   @RequestParam("pgLimit") String  pgLimit) {

        LOG.error("inside the Order table filter load");
        //cast the initial page and page limits in pagination to integers
        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pgLimit);
        LOG.error("processOrder filter table params{}, {} ,{}",searchDelivery,limitPg,initPage);

        List<Map<String, Object>> ordersByStatus=deliveryRepository.selectDeliveryDetailsByStatus(searchDelivery, limitPg, initPg);
        LOG.error("the filter by status output: {}",ordersByStatus);
        return ordersByStatus;
    }

    //to view the order process table count WHEN filtered by Delivery Status
    @RequestMapping(value = "/delivery/filteredCount", method = RequestMethod.GET)
    public
    @ResponseBody int deliveryFilteredCount(@RequestParam("searchDelivery ") String searchDelivery){
        int count=deliveryRepository.countDeliveryDetailsByStatus(searchDelivery);
        return count;
    }

}
