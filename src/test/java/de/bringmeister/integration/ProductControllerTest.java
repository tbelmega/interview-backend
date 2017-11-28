package de.bringmeister.integration;

import com.jayway.jsonpath.JsonPath;
import de.bringmeister.Application;
import org.apache.catalina.connector.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.core.IsCollectionContaining.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.assertThat;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    /** Spring starts the application on a random port and injects the port number here. */
    @LocalServerPort
    int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpEntity<String> entity;


    @Before
    public void setup(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("accept", "application/json");

        entity = new HttpEntity<String>(null, headers);
    }

    @Test
    public void testThat_GetRetrieves_BananaAndTomato() throws Exception {

        // act
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/products"),
                HttpMethod.GET, entity, String.class);


        // assert
        List<String> productNames  = JsonPath.read(response.getBody(), "$..name");
        assertThat(productNames, hasItem("Banana"));
        assertThat(productNames, hasItem("Tomato"));
    }

    @Test
    public void testThat_GetReturns_200_OK() throws Exception {

        // act
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/products"),
                HttpMethod.GET, entity, String.class);


        // assert
        assertThat(response.getStatusCode().value(), is(equalTo(Response.SC_OK)));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }


}
