package NumberOne;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPRequest {

	int id;
	@Test(priority=1)
	public void getUser() {   //get request
		
		given()
		
		.when()
		.get("https://reqres.in/api/users?page=2")
		.then()
		.statusCode(200)
		.body("page",equalTo(2))
		.log().all();
	}
		
	//@Test(priority=2)
		public void createUsers() { //post request
			
		HashMap data = new HashMap();      //storing the body values using Hashmap
		data.put("name", "Akhil");
		data.put("Job", "Student");
			id=given()                           //returning the id value to given method which is captured from the response
			.contentType("application/json")      //we are passing Json structure we need to specify this way
			.body(data)                           //body variable we are passing
			.when()
			.post("https://reqres.in/api/users")         //post request
			.jsonPath().getInt("id")  ;             //from post request from Json structure getting the value i.e ID which is of INt type so use getINt() and return the value 
			                                              //if its of string type use getString()
//		.then()
//		.statusCode(201)
//		.log().all();
		}
	//@Test(priority=3,dependsOnMethods= {"createUsers"}) //this method is dependent on above createuser method if and only if above method passed this update method will be executed
	public void updateUser() {    //create user above method is linked to this update user method 
		
		HashMap data = new HashMap();
		data.put("name", "Akhilkumar");
		data.put("Job", "Student123");
			given()                           
			.contentType("application/json")      
			.body(data)                          
			.when()
			.put("https://reqres.in/api/users/"+id)         //put request and  hear id value we are getting dynamically and fetching values body related to generate id and updating the respective body      
			.then()
		.statusCode(200)
		.log().all();
	}
	//@Test(priority=4)	
	public void deleteUser() {
		given()                                                   
		.when()
		.delete("https://reqres.in/api/users/"+id)
		.then()
		.statusCode(204)
		.log().all();
	}

}
