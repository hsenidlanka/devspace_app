package hsenid.web.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

public class TestSL4J {

    final static Logger logger = LoggerFactory.getLogger(TestSL4J.class);

    public static void main(String[] args) {

        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(100);
        logger.info("Temperature set to {}. Old temperature was {}.", randomGenerator.nextInt(100), randomGenerator.nextInt(100));
    }
}
