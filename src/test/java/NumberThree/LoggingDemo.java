package NumberThree;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class LoggingDemo {

	@Test
	public void logTest() {
		
		given()
		.when()
		.get("https://www.google.com/")
		.then()                       //different log methods we have using those we can print only body,headers,cookies etc from response 
		         
		//.log().all();               //used to print all the response
		//.log().body()              //used to print only body from response
		//.log().cookies()          //used to print only cookies
		.log().headers();          //used to print only headers
	}
}
