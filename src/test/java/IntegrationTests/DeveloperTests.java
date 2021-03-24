package IntegrationTests;

import models.Developer;
import java.io.IOException;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


public class DeveloperTests {

    Developer developer;
    String baseURL = "https://tech-services-1000201953.uc.r.appspot.com/";

    @BeforeSuite
    public void setup(){
        developer = new Developer("","","",0);
    }

    @Test
    public void postDeveloper_postsDeveloper() throws IOException {
        // create a client
        Response response =  given()
                .header("Content-type", "application/json")
                .and()
                .body(developer)
                .when()
                .post(baseURL+"developer")
                .then()
                .extract().response();

        developer = response.getBody().as(Developer.class);
        assertEquals(response.statusCode(),200);
        assertEquals(developer.getFirstName(),"");
        assertNotNull(developer.getId());

    }

}
