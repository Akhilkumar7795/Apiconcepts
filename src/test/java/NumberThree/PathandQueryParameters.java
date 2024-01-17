package NumberThree;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathandQueryParameters {

	@Test
	public void testPathandQueryParameters() {
		
		//https://reqres.in/api/users?page=2&id=5
		
		given()
		.pathParam("mypath", "users")    //Path parameters
		.queryParam("page", 2)           //query parameter
		.queryParam("id", 5)
		.when() 
		.get("https://reqres.in/api/{mypath}")    //hear we removed query parameters because that are already defined and it will go along with the request 
		                                          //we need to specify only path parameter in the curly braces
		                                          //query parameters are those which can't be changed that shd go as it is in the URL hence deleted 
		.then()
		.statusCode(200)
		.log().all();
		
	}
}
