package hsl.devspace.app.coreserver.common;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


/**
 * Created by hsenid on 6/29/16.
 */
public class InitServer {
    private static final Logger log = LogManager.getLogger(InitServer.class);
    private int port;
    private String packageName;
    private String contextRoot;
    private String servletPath;

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

    public Server createServer() {
//        ResourceConfig config = new ResourceConfig();
//        config.packages(getPackageName());
//        ServletHolder servlet = new ServletHolder(new ServletContainer(config));
//        Server server = new Server(getPort());
//        ServletContextHandler context = new ServletContextHandler(server, getContextRoot());
//        context.addServlet(servlet, getServletPath());

        ServletHolder sh = new ServletHolder(ServletContainer.class);
        sh.setInitParameter("com.sun.jersey.config.property.packages", getPackageName());//Set the package where the services reside
        sh.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");

        Server server = new Server(2222);
        ServletContextHandler context = new ServletContextHandler(server, getContextRoot(), ServletContextHandler.SESSIONS);
        context.addServlet(sh, getServletPath());

        return server;

    }
}