package hsl.devspace.app.coreserver.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tanukisoftware.wrapper.WrapperListener;
import org.tanukisoftware.wrapper.WrapperManager;

/**
 * Created by hsenid on 6/13/16.
 */
public class ServerMain implements WrapperListener {
    private static final Logger log = LogManager.getLogger(ServerMain.class);
    private Server server;

    @Override
    // Start wrapper
    public Integer start(String[] strings) {
        log.info("Start core server");
        ApplicationContext context=Context.appContext;
        InitServer initServer = (InitServer) context.getBean("init-server");
        server = initServer.createServer();
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            log.error("Error while starting jetty server. " + e);
        }
        return null;
    }

    @Override
    // Stop wrapper
    public int stop(int i) {
        log.error("Error...");
        try {
            server.stop();
        } catch (Exception e) {
            log.error("Error while stopping jetty server. " + e);
        }
        return i;
    }

    @Override
    public void controlEvent(int i) {
        /*if ((i == WrapperManager.WRAPPER_CTRL_LOGOFF_EVENT)
                && WrapperManager.isLaunchedAsService()) {
            // Ignore
        } else {
            WrapperManager.stop(0);
        }*/
    }

    public static void main(String[] args) throws Exception {
        WrapperManager.start(new ServerMain(), args);
    }
}
