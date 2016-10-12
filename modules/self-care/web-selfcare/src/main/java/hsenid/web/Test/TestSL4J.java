package hsenid.web.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:config.properties")
public class TestSL4J {
    @Value("${knock}")
     static String knock;

    final static Logger logger = LoggerFactory.getLogger(TestSL4J.class);

    public static void main(String[] args) {

        logger.info(knock);

    }
}
