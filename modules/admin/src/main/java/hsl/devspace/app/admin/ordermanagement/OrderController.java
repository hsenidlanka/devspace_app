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

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        modelview.setViewName("/order_management/orderView");
        return modelview;
    }

    //to view the order table
    @RequestMapping(value = "/view/orderTable", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Map<String, Object>> viewOrder(HttpServletRequest request) {

        List<Map<String, Object>> ordersList = null;

        try{
            String pgInit = request.getParameter("initPage");
            int initPg = Integer.parseInt(pgInit);
            String pgLimt = request.getParameter("pgLimit");
            int pgLimit = Integer.parseInt(pgLimt);

            String frmDate = request.getParameter("fromDate");
            String toDate = request.getParameter("todate");

            if((frmDate==null || frmDate.equals("")) && (toDate==null || toDate.equals(""))){

                ordersList = shoppingCart.selectAllOrdersPaginate(pgLimit, initPg);
                LOGGER.info("View all order details {} ", ordersList);
            }
            if(!(frmDate==null || frmDate.equals("")) && !(toDate==null || toDate.equals(""))) {
                SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy");
                Date fromDate2= sdf.parse(frmDate);
                Date toDate2= sdf.parse(toDate);
                java.sql.Date sqlfromDate= new java.sql.Date(fromDate2.getTime());
               java.sql.Date sqltoDate= new java.sql.Date(toDate2.getTime());

               // java.sql.Date sqlfromDate=  java.sql.Date.valueOf(frmDate);
             //   java.sql.Date sqltoDate=  java.sql.Date.valueOf(toDate);

                LOGGER.error("sqlOrderDate from {}, to{} ", sqlfromDate,sqltoDate);
                ordersList= shoppingCart.selectAllOrdersByDateRange( sqlfromDate, sqltoDate,pgLimit,initPg);
                LOGGER.info("View date-ranged order details {} ", ordersList);
            }
        } catch (Exception ex){
            LOGGER.error("Error occurred in view-order : {} ", ex);
        }
        return ordersList;
    }

    //get count of all orders
    @RequestMapping(value = "/orderPaginationTable", method = RequestMethod.GET)
    public @ResponseBody int countAllOrders(){

        int ordrCount = 0;
        ordrCount = shoppingCart.countTotalCustomerCarts();
        LOGGER.trace("Count of all orders : {}",ordrCount);
        return ordrCount;
    }

}
