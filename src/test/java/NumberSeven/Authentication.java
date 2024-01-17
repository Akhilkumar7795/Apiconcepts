package NumberSeven;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Authentication {

	//below 3 basic digest preemptive authentications are similar to one another but internally algorithm wise these are different used for user validations mostly
	//postman has given an api which supports below mentioned 3 authentications which is used in below methods
	//for most secured authorization we use oauth2.0
	//@Test(priority=1)
	public void testBasicAuthentication() {
		
		given()
		.auth().basic("postman", "password")
		.when()
		.get("https://postman-echo.com/basic-auth")
		.then()
		.statusCode(200)
		.body("authenticated", equalTo(true))
		.log().all();
	}
	
	//@Test(priority=2)
	public void testDigestAuthentication() {
		
		given()
		.auth().digest("postman", "password")
		.when()
		.get("https://postman-echo.com/basic-auth")
		.then()
		.statusCode(200)
		.body("authenticated", equalTo(true))
		.log().all();
	}
	//@Test(priority=3)
	public void testPreemptiveAuthentication() {
		
		given()
		.auth().preemptive().basic("postman","password")   //combination of both basic and digest
		.when()
		.get("https://postman-echo.com/basic-auth")
		.then()
		.statusCode(200)
		.body("authenticated", equalTo(true))
		.log().all();
}
	//hear we need to have a token
	//to get token goto ur github account > setting > developer settings > personal access and generate token use that token for validation
	//@Test(priority=4)
	public void testBearerAuthentication() {
		
     String bearerToken="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";    //this is the token generated
		
		given()
			.headers("Authorization","Bearer "+bearerToken)          //in postman we pass bearer token information as key value in headers hence hear we used header 
		
		.when()
			.get("https://api.github.com/user/repos")     //repo url request
			
		.then()
			.statusCode(200)
			.log().all();
	}
	//oauth1 is old version many applications using oauth2 
	//in oauth1 we need to specify more number of parameters as shown below that is the major disadvantage and in oauth2 we need to specify only accesstoken and this is widely used
	//if we have requirement to use oauth1 we need to follow below syntax all below keys information will be provided by developer
	    // @Test
		void testOAuth1Authentication()
		{
			given()
				.auth().oauth("consumerKey","consumerSecrat","accessToken","tokenSecrate") // this is for OAuth1.0 authentication
			.when()
				.get("url")
			.then()
				.statusCode(200)
				.log().all();
		}
	  // @Test
	 	void testOAuth2Authentication()
	 	{
	 		given()
	 		.auth().oauth2("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")    //only access token parameter is used which is generated from postman which is given by developer
	 	.when()
	 		.get("https://api.github.com/user/repos")
	 	.then()
	 		.statusCode(200)
	 		.log().all();
	 	}
	 	
	 	//testAPIKeyAuthentication
	 	
	   @Test
		void testAPIKeyAuthentication()
		{
			//Method1
			given()
				.queryParam("appid","fe9c5cddb7e01d747b4611c3fc9eaf2c") //appid is APIKey which needs to be passed in queryparameter in postman
			.when()
				.get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")  
			.then()
				.statusCode(200)
				.log().all();
			
			//https://api.openweathermap.org-------domain/host
			//data/2.5/forecast/daily-------path parameter
			//q=Delhi ------ query parameter
			//units=metric------ query parameter
			//cnt=7--query parameter
			
			//Method2
			
		given()
				.queryParam("appid","fe9c5cddb7e01d747b4611c3fc9eaf2c")
				
				.pathParam("mypath","data/2.5/forecast/daily")
			
				.queryParam("q", "Delhi")
				
				.queryParam("units", "metric")				
			    .queryParam("cnt", "7")
			
			.when()
				.get("https://api.openweathermap.org/{mypath}")   //hear we are specifying domain and pathparameter this is manadatory and query parameter which go along with the request
			
			.then()
				.statusCode(200)
			.log().all();	
			
		}
}
