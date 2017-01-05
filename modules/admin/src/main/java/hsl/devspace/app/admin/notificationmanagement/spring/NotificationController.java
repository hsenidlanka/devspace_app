package hsl.devspace.app.admin.notificationmanagement.spring;

import hsl.devspace.app.corelogic.repository.feedback.FeedbackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 11/21/16.
 */

@Controller
@RequestMapping("/notification")
public class NotificationController {

    //logger for the NotificationController class
    private static final Logger LOG = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private FeedbackRepository feedbackRepository;

    //handler method to display the users' comments
    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public String showCommentsPage(){
        return "notification_management/pendingComments";
    }


    //handler method to display the users' comments
    @RequestMapping(value = "/commentsView", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> showCommentsTable( @RequestParam("initPage") String initPage,
                                                 @RequestParam("pgLimit") String  pgLimit){

        //cast the initial page and page limits in pagination to integers
        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pgLimit);

        List<Map<String, Object>> commentList = feedbackRepository.view(limitPg,initPg);
        LOG.error("NOTIFICATION COMMENTS table{}",commentList);

        return commentList ;
    }

    /**
    *getting record count for loading comments table with pagination
    **/
    @RequestMapping(value = "/CommentsPaginationTable", method = RequestMethod.GET)
    public @ResponseBody int loadPagination(){

        LOG.error("Comments Count is {}",feedbackRepository.count());
        return feedbackRepository.count();
    }

    /**
     *method to approve the comment selected
     **/
    @RequestMapping(value = "/commentApprove", method = RequestMethod.GET)
    public @ResponseBody int approveComment(@RequestParam("cmntId") String cmntId){

        int cmntId2=Integer.parseInt(cmntId);
        int approveState= feedbackRepository.changeStatus(cmntId2,"active");
        LOG.error("Comment approve Result: {}",approveState);
        return approveState;
    }

    /**
     *method to discard the comment selected
     **/
    @RequestMapping(value = "/commentDiscard", method = RequestMethod.GET)
    public @ResponseBody int discardComment(@RequestParam("dcmntId") String dcmntId){

        int dcmntId2=Integer.parseInt(dcmntId);
        int discardState= feedbackRepository.changeStatus(dcmntId2,"block");
        LOG.error("Comment discard Result: {}",discardState);
        return discardState;
    }

    /** handler  method to filter the comments on posted customer username on typeahead **/
    @RequestMapping(value = "/commentsView/typeheadName/data", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> typeheadNameFilterData (@RequestParam("cmntName") String cmntName,
                                                      @RequestParam("pageLimit") String pageLimit,
                                                      @RequestParam("initPage") String initPage){
        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pageLimit);

        List<Map<String, Object>> nameList=feedbackRepository.selectFeedbacksByCustomerTypeAhead(cmntName,limitPg,initPg);
        return  nameList;
    }


    /** handler  method to filter the comments on posted customer username on typeahead **/
    @RequestMapping(value = "/commentsView/date/status", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> dateStatusFilterData (@RequestParam("from") String from,@RequestParam("to") String to,
                                                    @RequestParam("status") String status,
                                                    @RequestParam("pageLimit") String pageLimit,
                                                    @RequestParam("initPage") String initPage) throws ParseException {
        int initPg = Integer.parseInt(initPage);
        int limitPg = Integer.parseInt(pageLimit);


        //convert java.util time to sql time
        SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy");
        Date fromDate= sdf.parse(from);
        Date toDate= sdf.parse(to);
        java.sql.Date sqlfromDate= new java.sql.Date(fromDate.getTime());
        java.sql.Date sqltoDate= new java.sql.Date(toDate.getTime());

        List<Map<String, Object>> outCommentSearch = new ArrayList<Map<String, Object>>();

        //date range is selected
        if((!(from.equals(""))) && (!(to.equals(""))) && ((status.equals("--Select--")))) {

            LOG.error("date range selected: from{}, to{}",from,to);
            List<Map<String, Object>> outDateRange=feedbackRepository.selectFeedbacksByDateRange(sqlfromDate, sqltoDate, limitPg, initPg);
            return outDateRange;
        }

        //status is selected
        if(((from.equals(""))) && ((to.equals(""))) && (!(status.equals("--Select--")))) {

            LOG.error("status {}", status);
            List<Map<String, Object>> outStatus = feedbackRepository.filterFeedbacksByStatus(status, limitPg, initPg);
            return outStatus;
        }

        //both the city and date range are selected
        if((!(from.equals(""))) && (!(to.equals(""))) && (!(status.equals("--Select--")))) {

            LOG.error(" Date range and status selected: status{}, from:{}, to{}", status,from,to);
            List<Map<String, Object>> outCityDate=feedbackRepository.selectFeedbacksByDateRangeAndStatus(sqlfromDate,sqltoDate,status,limitPg,initPg);
            return outCityDate;
        }

        return outCommentSearch;
    }


//////////////////////////////////////////////// RATING MANAGEMENT////////////////////////////////////////////////////////////

    //handler method to display the users' cratings
    @RequestMapping(value = "/ratings", method = RequestMethod.GET)
    public String showRatingsPage(){
        return "notification_management/pendingRating";
    }
}
