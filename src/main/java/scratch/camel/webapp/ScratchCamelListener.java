package scratch.camel.webapp;

import org.apache.camel.component.servletlistener.JndiCamelServletContextListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

/**
 * @author Karl Bennett
 */
@WebListener // Register the Camel context listener.
public class ScratchCamelListener extends JndiCamelServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        // Register the Camel route builder that will configure the HTTP, JMS, and JPA endpoints.
        sce.getServletContext().setInitParameter("routeBuilder-scratch", "scratch.camel.webapp.ScratchRouteBuilder");

        super.contextInitialized(sce);
    }
}
