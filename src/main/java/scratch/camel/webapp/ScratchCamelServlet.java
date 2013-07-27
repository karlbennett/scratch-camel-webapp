package scratch.camel.webapp;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;

import javax.servlet.annotation.WebServlet;

/**
 * @author Karl Bennett
 */
@WebServlet("/scratch/*") // Register the Camel servlet.
public class ScratchCamelServlet extends CamelHttpTransportServlet {
}
