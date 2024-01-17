package NumberTwo;

import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import net.minidev.json.JSONObject;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

//DIFFERENT ways to create post request body
//1)Post request body creation  using HashMap  ("Suitable when we have small set of data")
//2)Post request body creation  using org.JSON
//3)Post request body creation  using POJO class
//4)Post request body creation  using external file data

public class waysToCreatePostRequestBody {
	
	//1)Post request body creation  using HashMap
	//@Test(priority=1)
	public void testPostRequestUsingHashMap() {
		
		HashMap data = new HashMap();
		data.put("name", "Akhil");
		data.put("location", "Ballari");
		data.put("phone", "23455");
		
		String courseArr[] = {"C","C++"};   //creating array and storing the array of objects [when we have multiple set of data we use this array approach]
		data.put("courses", courseArr);   //using same put method we are access courses variable
	
		given()
		.contentType("application/json")
		.body(data)
		.when()
		.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("name",equalTo("Akhil"))             //validating Json variables
		.body("location",equalTo("Ballari"))
		.body("phone",equalTo("23455"))
		.body("courses[0]",equalTo("C"))
		.body("courses[1]",equalTo("C++"))
		.header("Content-Type", "application/json; charset=utf-8")   //validating header 
		                  //[name of the header and value type]
		.log().all();
	}
//--------------------------------------------------------------------------------------------------------------------------------------	
	//2)Post request body creation  using Json library
	//@Test(priority=1)
	public void testPostRequestUsingJsonLibrary() {
		
		JSONObject data = new JSONObject();
		data.put("name", "Akhil");
		data.put("location", "Ballari");
		data.put("phone", "23455");
		
		String courseArr[] = {"C","C++"};   //creating array and storing the array of objects [when we have multiple set of data we use this array approach]
		data.put("courses", courseArr);  
	
		given()
		.contentType("application/json")
		.body(data.toString())         //hear we are converting data to string format first and then to json format this step is mandatory hear using JSON library
		.when()
		.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("name",equalTo("Akhil"))             //validating Json variables
		.body("location",equalTo("Ballari"))
		.body("phone",equalTo("23455"))
		.body("courses[0]",equalTo("C"))
		.body("courses[1]",equalTo("C++"))
		.header("Content-Type", "application/json; charset=utf-8")   //validating header 
		                  //[name of the header and value type]
		.log().all();
	}
	//--------------------------------------------------------------------------------------------------------------------------------------	
		//3)Post request body creation  using POJO class  [this can be achieved using encapsulation concept by using setters and getters]
		@Test(priority=1)
		public void testPostRequestPOJO() {
			
			Pojo_Postrequest data = new Pojo_Postrequest();
			data.setName("Akhil");
			data.setLocation("Ballari");
			data.setPhone("23455");
			String CoursesArr[]= {"C","C++"};
			data.setCourses(CoursesArr);
			
			given()
			.contentType("application/json")
			.body(data)         
			.when()
			.post("http://localhost:3000/students")
			.then()
			.statusCode(201)
			.body("name",equalTo("Akhil"))             //validating Json variables
			.body("location",equalTo("Ballari"))
			.body("phone",equalTo("23455"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type", "application/json; charset=utf-8")   //validating header 
			                  //[name of the header and value type]
			.log().all();
		}
		//--------------------------------------------------------------------------------------------------------------------------------------	
				//4)Post request body creation  using JSON  
				@Test(priority=1)
				public void testPostRequestJSON() throws FileNotFoundException  {
					
					File f = new File(".\\body.json");
					FileReader fr = new FileReader(f);
					JSONTokener jt = new JSONTokener(fr);
					//JSONObject data = new JSONObject(jt);
					
					given()
					.contentType("application/json")
					//.body(data.toString())         
					.when()
					.post("http://localhost:3000/students")
					.then()
					.statusCode(201)
					.body("name",equalTo("Akhil"))             //validating Json variables
					.body("location",equalTo("Ballari"))
					.body("phone",equalTo("23455"))
					.body("courses[0]",equalTo("C"))
					.body("courses[1]",equalTo("C++"))
					.header("Content-Type", "application/json; charset=utf-8")   //validating header 
					                  //[name of the header and value type]
					.log().all();
				}
		
	//deleting the student record
		@Test(priority=2)
		public void delete() {
			
			given()
			.when()
			.delete("http://localhost:3000/students/4")
			.then()
			.statusCode(200);	
		}
}
