package scratch.camel.webapp;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;

import javax.servlet.annotation.WebServlet;

/**
 * @author Karl Bennett
 */
@WebServlet("/scratch/*")
public class ScratchCamelServlet extends CamelHttpTransportServlet {

    public ScratchCamelServlet() {

        System.out.println("SERVLET STARTORED!");
    }
}
