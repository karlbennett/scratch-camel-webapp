package scratch.camel.webapp;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;

import javax.servlet.annotation.WebServlet;

/**
 * The Camel servlet that will accept all the RESTful requests.
 *
 * @author Karl Bennett
 */
@WebServlet("/scratch/*") // Register the Camel servlet.
public class ScratchCamelServlet extends CamelHttpTransportServlet {
}
