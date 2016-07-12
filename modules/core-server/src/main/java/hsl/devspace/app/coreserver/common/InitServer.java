package hsl.devspace.app.coreserver.common;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


/**
 * Created by hsenid on 6/29/16.
 * This class has the configuration for the Jetty server.
 */
public class InitServer {
    private static final Logger log = LogManager.getLogger(InitServer.class);
    private int port;   // Jetty server port number
    private String packageName; // Package contains resources(REST)
    private String contextRoot; // Context path pattern
    private String servletPath; // Servlet path-url pattern

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getContextRoot() {
        return contextRoot;
    }

    public void setContextRoot(String contextRoot) {
        this.contextRoot = contextRoot;
    }

    public String getServletPath() {
        return servletPath;
    }

    public void setServletPath(String servletPath) {
        this.servletPath = servletPath;
    }

    // Create and return a Jetty server instance under the port number defined.
    public Server createServer() {
        ServletHolder sh = new ServletHolder(ServletContainer.class);
        sh.setInitParameter("com.sun.jersey.config.property.packages", getPackageName());//Set the package where the services reside
        sh.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");

        Server server = new Server(getPort());
        ServletContextHandler context = new ServletContextHandler(server, getContextRoot(), ServletContextHandler.SESSIONS);
        context.addServlet(sh, getServletPath());

        return server;
    }
}