package scratch.camel.webapp;

import org.apache.camel.builder.RouteBuilder;

/**
 * This Camel {@link RouteBuilder} will register all the RESTful endpoints.
 *
 * @author Karl Bennett
 */
public class ScratchRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        System.out.println("ROUTE BUILDER STARTORED!");
    }
}
