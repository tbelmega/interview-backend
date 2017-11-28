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
import static org.hamcrest.core.StringContains.*;

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
    public void testThat_GetAll_RetrievesBananaAndTomato() throws Exception {

        // act
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/products"),
                HttpMethod.GET, entity, String.class);


        // assert
        List<String> productNames  = JsonPath.read(response.getBody(), "$..name");
        assertThat(productNames, hasItem("Banana")); // see products.xml
        assertThat(productNames, hasItem("Tomato"));
    }

    @Test
    public void testThat_GetAll_Returns200OK() throws Exception {

        // act
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/products"),
                HttpMethod.GET, entity, String.class);


        // assert
        assertThat(response.getStatusCode().value(), is(equalTo(Response.SC_OK)));
    }

    @Test
    public void testThat_GetById_Returns200OK() throws Exception {

        // arrange
        String bananaId = "43b105a0-b5da-401b-a91d-32237ae418e4"; // see products.xml

        // act
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/products/" + bananaId),
                HttpMethod.GET, entity, String.class);

        // assert
        assertThat(response.getStatusCode().value(), is(equalTo(Response.SC_OK)));
    }

    @Test
    public void testThat_GetById_ReturnsBanana() throws Exception {

        // arrange
        String bananaId = "43b105a0-b5da-401b-a91d-32237ae418e4"; // see products.xml

        // act
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/products/" + bananaId),
                HttpMethod.GET, entity, String.class);

        // assert
        String productName  = JsonPath.read(response.getBody(), "$.product.name");
        assertThat(productName, is(equalTo("Banana")));
    }

    @Test
    public void testThat_GetById_ReturnsPrices() throws Exception {

        // arrange
        String bananaId = "43b105a0-b5da-401b-a91d-32237ae418e4"; // see products.xml

        // act
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/products/" + bananaId),
                HttpMethod.GET, entity, String.class);

        // assert
        int numberOfPrices  = JsonPath.read(response.getBody(), "$.prices.length()");
        assertThat(numberOfPrices, is(equalTo(2)));
    }

    @Test
    public void testThat_GetByUnknownId_Returns404NotFound() throws Exception {

        // arrange
        String id = "some-non-existing-id";

        // act
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/products/" + id),
                HttpMethod.GET, entity, String.class);

        // assert
        assertThat(response.getStatusCode().value(), is(equalTo(Response.SC_NOT_FOUND)));
    }


    @Test
    public void testThat_GetByIdAndUnitPackage_ReturnsAPackagePrice() throws Exception {

        // arrange
        String bananaId = "43b105a0-b5da-401b-a91d-32237ae418e4"; // see products.xml

        // act
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/products/" + bananaId + "/prices/package"),
                HttpMethod.GET, entity, String.class);

        // assert
        String unit  = JsonPath.read(response.getBody(), "$.unit");
        assertThat(unit, is(equalTo("package")));
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }


}
