package hsl.devspace.app.corelogic.repository.feedback;

import hsl.devspace.app.corelogic.domain.Feedback;

import java.util.List;
import java.util.Map;

/**
 * Created by hsenid on 9/19/16.
 */
public interface FeedbackRepository {
    int add(Feedback feedback);

    List<Map<String, Object>> view();
}
