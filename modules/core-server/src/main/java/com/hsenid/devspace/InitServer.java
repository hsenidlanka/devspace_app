package com.hsenid.devspace;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import com.sun.jersey.spi.container.servlet.ServletContainer;

/**
 * Created by hsenid on 6/29/16.
 */
public class InitServer {
    private static final Logger log = LogManager.getLogger(InitServer.class);
    private int port;
    private String packageName;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public Server createServer() {
        ResourceConfig config = new ResourceConfig();
        config.packages(this.packageName);
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));
        Server server = new Server(this.port);
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");
        return server;
    }
}
