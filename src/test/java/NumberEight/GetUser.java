package NumberEight;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;
public class GetUser {

	@Test
	public void testGetUser(ITestContext context) {
		
	//int id = (Integer) context.getAttribute("user_id");  //this id should come from createuser request and get the id using getattribute method and pass the varaible where is stored in createuser test
	
	// Chain (Share Data with) APIs in same Suite Tag	
			 int id = (Integer) context.getSuite().getAttribute("user_id");
			 
   String bearerToken="c35e10e748c6f113775527bcef204e9929b4c9f4b995a8ee253eec46aed57b06";  // github token get from postman
		
   given()
   .headers("Authorization","Bearer " +bearerToken)
   .pathParam("id", id)
   .when()
   .get("https://gorest.co.in/public/v2/users/{id}")  //path parameter id we are passing hear
		       .then()
		       .statusCode(200)
		       .log().all();
	}
}
