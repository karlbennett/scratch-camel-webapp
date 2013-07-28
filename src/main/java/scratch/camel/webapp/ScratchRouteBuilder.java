package scratch.camel.webapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * This Camel {@link RouteBuilder} will register all the RESTful endpoints.
 *
 * @author Karl Bennett
 */
public class ScratchRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        UserPoller poller = new UserPoller(
                getContext().createConsumerTemplate(),
                getContext().createProducerTemplate()
        );

        from("timer:poll?period=1s").bean(poller, "poll");
    }

    public static class User {

        private Long id;

        private String email;

        private String firstName;

        private String lastName;

        private Date created;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Date getCreated() {
            return created;
        }

        public void setCreated(Date created) {
            this.created = created;
        }
    }

    public static class UserPoller {

        private final ConsumerTemplate consumerTemplate;
        private final ProducerTemplate producerTemplate;

        private Date begin;
        private Date end;

        private int first = 0;

        public UserPoller(ConsumerTemplate consumerTemplate, ProducerTemplate producerTemplate) {

            this.consumerTemplate = consumerTemplate;
            this.producerTemplate = producerTemplate;
        }

        public void poll() throws IOException {

            String body = consumerTemplate
                    .receiveBody(
                            "http://localhost:8080/scratch-roo-webapp/users?firstResult=" + first + "&maxResults=100",
                            String.class
                    );

            List<User> users = new ObjectMapper().readValue(body, new TypeReference<List<User>>() {
            });

            if (0 < users.size()) {

                begin = users.get(0).getCreated();
                end = users.get(users.size() - 1).getCreated();

                body = consumerTemplate.receiveBody(
                        "http://localhost:8080/scratch-roo-webapp/users?first=" + begin.getTime() + "&last=" + end.getTime(),
                        1000,
                        String.class
                );

                users = new ObjectMapper().readValue(body, new TypeReference<List<User>>() {
                });

                first += users.size();
            }

            producerTemplate.sendBody("stream:out", body);
        }
    }
}
