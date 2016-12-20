package hsl.devspace.app.admin.ordermanagement;


import hsl.devspace.app.corelogic.domain.ShoppingCart;
import hsl.devspace.app.corelogic.repository.shopping_cart.ShoppingCartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/orders")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private ShoppingCartRepository shoppingCart;


    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView loadOrderView(ModelAndView modelview){

        modelview.addObject("command", new ShoppingCart());
        modelview.setViewName("/order_processing/orderView");
        return modelview;
    }

    //to view the order table
    @RequestMapping(value = "/view/orderTable", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Map<String, Object>> viewItem() {
        LOGGER.info("View all order details {} ", (shoppingCart.selectAll()));
        return shoppingCart.selectAll();
    }

}
