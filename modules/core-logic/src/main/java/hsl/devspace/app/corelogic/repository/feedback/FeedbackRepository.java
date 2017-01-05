package hsl.devspace.app.corelogic.repository.feedback;

import hsl.devspace.app.corelogic.domain.Feedback;

import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 9/19/16.
 */
public interface FeedbackRepository {
    int add(Feedback feedback);

    List<Map<String, Object>> view(int limit, int page);

    public int count();

    int changeStatus(int id, String status);

    List<Map<String, Object>> selectBlockedFeedbacks(int limit, int offset);

    List<Map<String, Object>> selectActiveFeedbacks(int limit, int offset);

    List<Map<String, Object>> selectInactiveFeedbacks(int limit, int offset);

    List<Map<String, Object>> selectFeedbacksByDateRange(java.sql.Date dateFrom, java.sql.Date dateTo, int limit, int offset);

    List<Map<String, Object>> selectFeedbacksByDateRangeAndStatus(java.sql.Date dateFrom, java.sql.Date dateTo, String status, int limit, int offset);

    List<Map<String, Object>> filterFeedbacksByStatus(String status, int limit, int offset);

    List<Map<String, Object>> selectFeedbacksByCustomerTypeAhead(String nameKey, int limit, int offset);
}
