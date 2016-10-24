package hsl.devspace.app.corelogic.repository.contact_us;

import hsl.devspace.app.corelogic.domain.ContactUs;

import java.util.List;

/**
 * Created by hsenid on 10/20/16.
 */
public interface ContactUsRepository {
    int add(ContactUs contact);
    List<ContactUs> selectAllToReview();
}
