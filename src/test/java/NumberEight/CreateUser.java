package NumberEight;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import com.github.javafaker.Faker;

//APICHAINING : from request we get response using this reponse as request for other request is called chaining
//EX: from one request we get id as response now use this id as request for other request and get the response
//https://gorest.co.in/public/v2/users --> in this example we are using gorestapi and using this in postman we get bearertoken generated which is used below
//in order to execute this apichaining 4 tests we need to create xml and run the xml

public class CreateUser {

	@Test
	public void testCreateUser(ITestContext context) {      //ITestContext is an interface from testng using this we can share response generated varaible id to other class tests
		
		Faker faker = new Faker();   //created faker object to create our own data
		
		JSONObject data = new JSONObject();   //created data we need to add into JSONobject
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status","inactive");
		
		String bearerToken="c35e10e748c6f113775527bcef204e9929b4c9f4b995a8ee253eec46aed57b06";  // github token get from postman
		
		int id=	given()
				.headers("Authorization","Bearer " +bearerToken)  //in postman in header section we need to pass bearer token and hence used header
				.contentType("application/json")
				.body(data.toString())                        // converting object data to string form 
			.when()
				.post("https://gorest.co.in/public/v2/users")    // go restapi passing as post request
				.jsonPath().getInt("id");    // from response we are capturing only id using jsonpath and returing it and hence used int as return type
		
		// Chain (Share Data with) APIs in same Test Tag and this is accessible only with in the test
		//context.setAttribute("user_id", id);   //hear we are setting id to userid variable which needs to be shared to other tests
		
		// Chain (Share Data with) APIs in same Suite Tag and can be accessible across suite level 
		//suite might contain multiple tests which runs indiviually refer testng2.xml file
		context.getSuite().setAttribute("user_id", id);	  //using only this statement we can execute both testng1 and testng2.xml 
	}
}
