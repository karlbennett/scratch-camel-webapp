package scratch.camel.webapp;

import org.apache.camel.component.servletlistener.JndiCamelServletContextListener;

import javax.servlet.annotation.WebListener;

/**
 * @author Karl Bennett
 */
@WebListener
public class ScratchCamelListener extends JndiCamelServletContextListener {

    public ScratchCamelListener() {

        System.out.println("LISTENER STARTORED!");
    }
}
