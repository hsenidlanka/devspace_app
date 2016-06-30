package com.hsenid.devspace;

import com.mchange.v2.c3p0.ComboPooledDataSource;
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
    Server server;
    ComboPooledDataSource comboPooledDataSource;

    @Override
    public Integer start(String[] strings) {
        log.info("Start core server");
        ApplicationContext appContext = new ClassPathXmlApplicationContext("spring-context.xml");
        InitServer initServer = (InitServer) appContext.getBean("init-server");
        server = initServer.createServer();
        try {
            server.start();
            server.join();
            comboPooledDataSource = (ComboPooledDataSource) appContext.getBean("datasource");
            comboPooledDataSource.getConnection();
        } catch (Exception e) {
            log.error("Error while starting jetty server. " + e);
        }
        return null;
    }

    @Override
    public int stop(int i) {
        WrapperManager.log(WrapperManager.WRAPPER_LOG_LEVEL_INFO, "Stop");
        log.error("SFSDSDFSDF");
        try {
            comboPooledDataSource.close();
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
