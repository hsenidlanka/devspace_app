package hsl.devspace.app.admin.notificationmanagement.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hsenid on 11/21/16.
 */

@Controller
@RequestMapping("/notification")
public class NotificationController {

    //logger for the NotificationController class
    private static final Logger LOG = LoggerFactory.getLogger(NotificationController.class);

    //handler method to display the users' comments
    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public String showCommentsPage(){
        return "notification_management/pendingComments";
    }

    //handler method to display the users' cratings
    @RequestMapping(value = "/ratings", method = RequestMethod.GET)
    public String showRatingsPage(){
        return "notification_management/pendingRating";
    }
}
