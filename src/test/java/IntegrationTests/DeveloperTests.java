package IntegrationTests;

import models.Developer;
import java.io.IOException;
import java.util.List;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


public class DeveloperTests {

    Developer developer;
    String baseURL = "https://tech-services-1000201953.uc.r.appspot.com/";
    String id = "";

    @BeforeSuite
    public void setup(){
        developer = new Developer("Ryan","Bingham","Python",2015);//update me
    }

    @Test(priority = 2)
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
        assertEquals(developer.getFirstName(),"Ryan");//update me
        assertEquals(developer.getYearStarted(), 2015);
        assertNotNull(developer.getId());
        id = developer.getId();

    }
    
    @Test(priority = 3)
    public void getDeveloperById_getsDeveloper(){
    	Response response =  given()
                .when()
                .get(baseURL+"developer/" + id)
                .then()
                .extract().response();

        developer = response.getBody().as(Developer.class);
        assertEquals(response.statusCode(),200);
        assertEquals(developer.getFirstName(),"Ryan");//update me
        assertEquals(developer.getLastName(), "Bingham");
    }
    
    @Test(priority = 4)
    public void putDeveloper_updatesdeveloper(){
    	developer.setYearStarted(2017);
    	
    	Response response =  given()
                .header("Content-type", "application/json")
                .and()
                .body(developer)
                .when()
                .put(baseURL+"developer")
                .then()
                .extract().response();

        developer = response.getBody().as(Developer.class);
        assertEquals(response.statusCode(),200);
        assertEquals(developer.getFirstName(),"Ryan");//update me
        assertEquals(developer.getYearStarted(), 2017);
    	
    }
    
    @Test(priority = 5)
    public void deleteDeveloper_deletesDeveloper(){
    	Developer deleted = new Developer("Firstname", "Lastname", "Java", 2020);
    	
    	Response initialResponse =  given()
                .header("Content-type", "application/json")
                .and()
                .body(deleted)
                .when()
                .post(baseURL+"developer")
                .then()
                .extract().response();
    	deleted = initialResponse.getBody().as(Developer.class);
    	String newId = deleted.getId();
    	
    	Response response =  given()
                .when()
                .delete(baseURL+"developer" + "/" + newId)
                .then()
                .extract().response();
    			
        //developer = response.getBody().as(Developer.class);
        assertEquals(response.statusCode(),200);
        assertEquals(response.getBody().asString(),"\"OK\"");//update me
        //assertEquals(developer.getYearStarted(), 2016);
    }
    
    @Test(priority = 1)
    public void getDevelopers_getsAllDevelopers(){
    	Response response =  given()
                .when()
                .get(baseURL+"developers")
                .then()
                .contentType(ContentType.JSON).extract().response();

         List<Developer> developers = response.jsonPath().getList("$");
        
         assertEquals(developers.size(), 385);
        //developer = developers.get(3);
        assertEquals(response.statusCode(),200);
        //assertEquals(developers.getFirstName(),"Dimao");//update me
        //assertEquals(developers.getYearStarted(), 2025);
    }

}
