package hsl.devspace.app.corelogic.repository.seasonal_discounts;

import hsl.devspace.app.corelogic.domain.SeasonalDiscount;

/**
 * Created by hsenid on 9/20/16.
 */
public interface SeasonalDiscountRepository {
    int add(SeasonalDiscount seasonalDiscount);
    int delete(int id);
    

}
