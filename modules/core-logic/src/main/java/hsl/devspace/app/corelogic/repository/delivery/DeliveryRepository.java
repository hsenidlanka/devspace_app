package hsl.devspace.app.corelogic.repository.delivery;

import hsl.devspace.app.corelogic.domain.Delivery;

import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 9/19/16.
 */
public interface DeliveryRepository {
    int add(Delivery delivery);
    int changeStatus(int id);
    int addDeliveryMethod(String deliveryMethod);
    int deleteDeliveryMethod(String deliveryMethod);

    List<Map<String, Object>> selectDeliveryDetails(int limit, int offset);

    List<String> selectDeliveryAgents(String branch);

}
