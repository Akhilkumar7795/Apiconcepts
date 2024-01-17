package NumberEight;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
public class UpdateUser {

	@Test  
	void test_updateuser(ITestContext context)
	{
		Faker faker=new Faker();	
		
		JSONObject data=new JSONObject();
			
			data.put("name",faker.name().fullName());
			data.put("gender","Male");
			data.put("email",faker.internet().emailAddress());
			data.put("status","active");
				
			String bearerToken="c35e10e748c6f113775527bcef204e9929b4c9f4b995a8ee253eec46aed57b06";  // github token
			//int id = (Integer) context.getAttribute("user_id");
			// Chain (Share Data with) APIs in same Suite Tag	
			 int id = (Integer) context.getSuite().getAttribute("user_id");
			given()
					.headers("Authorization","Bearer " +bearerToken)  //in postman in header section we need to pass bearer token and hence used header
					.contentType("application/json")
					.pathParam("id", id)
					.body(data.toString())                        // converting object data to string form 
				.when()
					.put("https://gorest.co.in/public/v2/users/{id}")    // go restapi passing as post request
					.then()
					.statusCode(200)
					.log().all();
				
}
	
}