package hsl.devspace.app.corelogic.repository.shopping_cart;

import org.slf4j.LoggerFactory;

/**
 * Created by hsenid on 12/20/16.
 */
public class OrderIdGenerator {
    org.slf4j.Logger log = LoggerFactory.getLogger(OrderIdGenerator.class);

    String formatted;
    String orderId = "PS";

    public String generateOrderId(String order) {
        String s = order.substring(2);
        s = String.valueOf(Integer.parseInt(s) + 1);
        int lng = 5 - s.length();
        if (lng == 0) {
            log.info("s={}", s);
            orderId = orderId + s;

        } else {
            formatted = String.format("%0" + lng + "d", 0);
            orderId = orderId + formatted + s;

        }
        log.info("{}", orderId);
        return orderId;

    }

}
