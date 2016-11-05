package hsenid.web.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @RequestMapping(method = RequestMethod.GET)
    public String shoppingCartMethod(){
        return "/shopping-cart";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String shoppingCartPost(){
        return "/shopping-cart";
    }
}
