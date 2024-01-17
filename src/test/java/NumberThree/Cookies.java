package NumberThree;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.Map;

public class Cookies {

	//@Test(priority=1)
	 void testCookies() {
		
		given()
		.when()
		.get("https://www.google.com/")
		.then()
		.cookie("AEC","Ackid1RTokg1L7DwCZ8D4NerJqEJn20XwObPu5iUzyLKTVTZRAbHqwF0tQ")    //cookies for every run different values will be generated hence test fails hear
		.log().all();	
	}
	@Test(priority=2)
	void getCookiesinfo() {
		
		Response res=given()
		.when()
		.get("https://www.google.com/");   //hear we are ending statement and returing response and hence we used semicolon hear
		
	   String cookie_value=res.getCookie("AEC");   //getting single cookie value from request
		System.out.println("cookie value is "+cookie_value);
		
		Map<String,String> cookies_value=res.getCookies();   //getting all the cookie values from request
		System.out.println(cookies_value.keySet());       // this will return all the cookie keys [EX:1P_JAR, AEC, NID] 
		
		for(String k:cookies_value.keySet()) {
			String cookiee_value=res.getCookie(k);
			System.out.println(k+"        "+cookiee_value);
		}
	}
}
