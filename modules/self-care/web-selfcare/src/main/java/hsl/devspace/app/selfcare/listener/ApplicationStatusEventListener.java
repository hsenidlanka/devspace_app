package hsl.devspace.app.selfcare.listener;





import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationStatusEventListener implements ServletContextListener {



    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        logger.info("selfcare module started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        logger.info("selfcare module down");
    }
}

