package hsl.devspace.app.corelogic.repository.contact_us;

import hsl.devspace.app.corelogic.domain.ContactUs;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by hsenid on 10/20/16.
 */
public interface ContactUsRepository {
    int add(ContactUs contact);
    List<ContactUs> selectAll();
    List<ContactUs> selectAllToReview();
    List<ContactUs> selectAllReviewed();
    List<ContactUs> selectByDate(Date date);
    List<ContactUs> selectByType(String type);

    int delete(String username, Date date, Time time);

}
