package hsenid.web.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hsenid on 11/22/16.
 */
@RequestMapping(value = "/delivery")
public class DeliveryController {

    @RequestMapping(value = "/get-customer-data", method = RequestMethod.GET)
    @ResponseBody
    public void getCustomerDetails() {

    }
}
