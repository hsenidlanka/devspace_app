package com.hsenid.devspace;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tanukisoftware.wrapper.WrapperListener;
import org.tanukisoftware.wrapper.WrapperManager;

/**
 * Created by hsenid on 6/13/16.
 */
public class Demo implements WrapperListener {
    private static final Logger log = LogManager.getLogger(Demo.class);

    @Override
    public Integer start(String[] strings) {
        try {
            log.info("Core server is starting...");
            System.out.println("Hello World!");
            log.info("Core server started...");
        } catch (Exception e) {
            log.error("Error occured. " + e);
        }
        return null;
    }

    @Override
    public int stop(int i) {
        log.info("Core server stopped...");
        return 0;
    }

    @Override
    public void controlEvent(int i) {
        if ((i == WrapperManager.WRAPPER_CTRL_LOGOFF_EVENT)
                && WrapperManager.isLaunchedAsService()) {
            // Ignore
        } else {
            WrapperManager.stop(0);
        }
    }

    public static void main(String[] args) {
        WrapperManager.start(new Demo(), args);
    }
}
