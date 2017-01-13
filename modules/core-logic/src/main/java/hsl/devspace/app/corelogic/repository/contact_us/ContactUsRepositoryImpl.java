package hsl.devspace.app.corelogic.repository.contact_us;

import hsl.devspace.app.corelogic.domain.ContactUs;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 10/20/16.
 */
public class ContactUsRepositoryImpl implements ContactUsRepository   {
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;
    org.slf4j.Logger log = LoggerFactory.getLogger(ContactUsRepositoryImpl.class);


    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;

    }

    @Override
    public int add(ContactUs contact) {
        int row = 0;
        if (contact.getTitle() != "" && contact.getName() != "" && contact.getEmail() != "" && contact.getMobile() != "" && contact.getMessage() != "") {
            String sql = "INSERT INTO contact_us " +
                    "(type,title,name,email,mobile,message,date,time,status) VALUES (?,?,?,?,?,?,CURRENT_DATE,CURRENT_TIME,1)";

            row = jdbcTemplate.update(sql, new Object[]{contact.getInquiryType(), contact.getTitle(), contact.getName(), contact.getEmail(), contact.getMobile(), contact.getMessage()});
            log.info("{} new contactUs inquiry inserted", row);
        }

        return row;
    }

    @Override
    public List<ContactUs> selectAll() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM contact_us");
        log.info("{}",mp);
        List<ContactUs> contact=new ArrayList<ContactUs>();
        for (int i=0;i<mp.size();i++){
            ContactUs contactUs=new ContactUs();
            contactUs.setType(mp.get(i).get("type").toString());
            contactUs.setTitle(mp.get(i).get("title").toString());
            contactUs.setName(mp.get(i).get("name").toString());
            contactUs.setEmail(mp.get(i).get("email").toString());
            contactUs.setMobile(mp.get(i).get("mobile").toString());
            contactUs.setMessage(mp.get(i).get("message").toString());
            contact.add(contactUs);

        }
        log.info("{}",contact);
        return contact;
    }

    @Override
    public List<ContactUs> selectAllToReview() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM contact_us WHERE status=1");
        log.info("{}",mp);
        List<ContactUs> contact=new ArrayList<ContactUs>();
        for (int i=0;i<mp.size();i++){
            ContactUs contactUs=new ContactUs();
            contactUs.setType(mp.get(i).get("type").toString());
            contactUs.setTitle(mp.get(i).get("title").toString());
            contactUs.setName(mp.get(i).get("name").toString());
            contactUs.setEmail(mp.get(i).get("email").toString());
            contactUs.setMobile(mp.get(i).get("mobile").toString());
            contactUs.setMessage(mp.get(i).get("message").toString());
            contact.add(contactUs);

        }
        log.info("{}",contact);
        return contact;
    }

    @Override
    public List<ContactUs> selectAllReviewed() {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM contact_us WHERE status=2");
        log.info("{}",mp);
        List<ContactUs> contact=new ArrayList<ContactUs>();
        for (int i=0;i<mp.size();i++){
            ContactUs contactUs=new ContactUs();
            contactUs.setType(mp.get(i).get("type").toString());
            contactUs.setTitle(mp.get(i).get("title").toString());
            contactUs.setName(mp.get(i).get("name").toString());
            contactUs.setEmail(mp.get(i).get("email").toString());
            contactUs.setMobile(mp.get(i).get("mobile").toString());
            contactUs.setMessage(mp.get(i).get("message").toString());
            contact.add(contactUs);

        }
        log.info("{}",contact);
        return contact;
    }

    @Override
    public List<ContactUs> selectByDate(Date date) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM contact_us WHERE date=?",date);
        log.info("{}",mp);
        List<ContactUs> contact=new ArrayList<ContactUs>();
        for (int i=0;i<mp.size();i++){
            ContactUs contactUs=new ContactUs();
            contactUs.setType(mp.get(i).get("type").toString());
            contactUs.setTitle(mp.get(i).get("title").toString());
            contactUs.setName(mp.get(i).get("name").toString());
            contactUs.setEmail(mp.get(i).get("email").toString());
            contactUs.setMobile(mp.get(i).get("mobile").toString());
            contactUs.setMessage(mp.get(i).get("message").toString());
            contact.add(contactUs);

        }
        log.info("{}",contact);
        return contact;    }

    @Override
    public List<ContactUs> selectByType(String type) {
        List<Map<String, Object>> mp = jdbcTemplate.queryForList("SELECT * FROM contact_us WHERE type=?",type);
        log.info("{}",mp);
        List<ContactUs> contact=new ArrayList<ContactUs>();
        for (int i=0;i<mp.size();i++){
            ContactUs contactUs=new ContactUs();
            contactUs.setType(mp.get(i).get("type").toString());
            contactUs.setTitle(mp.get(i).get("title").toString());
            contactUs.setName(mp.get(i).get("name").toString());
            contactUs.setEmail(mp.get(i).get("email").toString());
            contactUs.setMobile(mp.get(i).get("mobile").toString());
            contactUs.setMessage(mp.get(i).get("message").toString());
            contact.add(contactUs);

        }
        log.info("{}",contact);
        return contact;
    }

    public int delete(String username, Date date, Time time) {
        String sql = "DELETE FROM contact_us WHERE name = ? AND date=? AND time=?";
        int row = jdbcTemplate.update(sql, new Object[]{username, date, time});
        log.info("{} contact deleted", row);
        return row;
    }


}
