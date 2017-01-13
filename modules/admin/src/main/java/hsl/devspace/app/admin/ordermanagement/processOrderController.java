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
    public @ResponseBody List<String> agentListRetrieve(@RequestParam("branch") String branch){
        LOG.error("inside the agent list retrieve method");
        List<String> agentsList=deliveryRepository.selectDeliveryAgents(branch);
        LOG.error("the delivery agents for {} are {}",branch,agentsList);
        return agentsList;
    }
}
