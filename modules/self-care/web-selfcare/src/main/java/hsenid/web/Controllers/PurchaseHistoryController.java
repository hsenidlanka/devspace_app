package hsenid.web.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PurchaseHistoryController {

    @RequestMapping(value = "purchase-history", method = RequestMethod.GET)
    public ModelAndView loadPurchaseHistoryPage() {
        ModelAndView modelAndView = new ModelAndView("includes/purchase-history");
        return modelAndView;
    }
}
