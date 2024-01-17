package Numberfour;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class ParsingJSONResponseData {

	//in this class we are creating store json and storing all books in that
	//now we need to fetch the particular value from the Json and validate
	//ex in json we need to validate title of the book in all multiple array of objects 
	//if in json there are multiple values so finding any required value out of it will be difficult and hence we use jsonpathfinder to fetch the required jsonpath

	//@Test(priority=1)
	public void jsonResponse() {
		//Approach 1 [using jsonpathfinder]
		given()
		.contentType("ContentType.JSON")
		.when()
		.get("http://localhost:3000/store")
		.then()     
		.statusCode(200)
		.header("Content-Type", "application/json; charset=utf-8") 
		//we need to paste the json in jsonpathfinder and get the required json path from it 
		.body("book[3].title",equalTo("The Lord of the Rings"))  //"book[3].title"[this is jsonpath] -- this we got from Jsonpathfinder 
		.log().all();
		
	}
	//JSON Object      [in json json object is starting from { and ending with } what ever present inside this is considered as json object      
	 //{
	//}
	//JSON Array [starting with [ and ending with ] considered as Json array 
		//[
		//]
		//JSON Element [combinataion of json object and json object]
	
	//EX :
//	{   ------ Json object starting
//	    "book": [   --- json array starting
	//inside json array we have json objets which are starting from {  and ending with }
//	        {
//	            "author": "Nigel Rees",
//	            "category": "reference",
//	            "price": 8.95,                         
//	            "title": "Sayings of the Century"
//	        },
//	        {
//	            "author": "Evelyn Waugh",
//	            "category": "fiction",
//	            "price": 12.99,
//	            "title": "Sword of Honour"
//	        },
//	        {
//	            "author": "Herman Melville",
//	            "category": "fiction",
//	            "isbn": "0-553-21311-3",
//	            "price": 8.99,
//	            "title": "Moby Dick"
//	        },
//	        {
//	            "author": "J. R. R. Tolkien",
//	            "category": "fiction",
//	            "isbn": "0-395-19395-8",
//	            "price": 22.99,
//	            "title": "The Lord of the Rings"
//	        }
//	    ] -- json array ending
//	} ---- json object ending
// and hence we write 
// JSON Object --JSONArray--- JSON Objects   in json object we have json array in json array we have json objects and required variable
//
// jo.getJSONArray("book").getJSONObject(i).get("title")
// jo.getJSONArray("book").getJSONObject(3).get("author")
	//and if json is starting with jsonarray then forst we need to create jsonarray object and access jsonobjects
	
//IMP	//one more example with json starting with json array and json objects and again json array present in it
//	[  -- json array starting 
//	    {
//	        "id": 1,
//	        "name": "John",
//	        "location": "india",
//	        "phone": "1234567890",
//	        "courses": [
//	            "Java",
//	            "Selenium"
//	        ]
//	    },
//	    {
//	        "id": 2,
//	        "name": "Kim",
//	        "location": "US",
//	        "phone": "98876543213",
//	        "courses": [
//	            "Python",
//	            "Appium"
//	        ] -- json array ending
//	    }
	//requiremnt to access selenium present in courses
// and hence we need to write first Jsonarray as
	//JSONArray jrr=new JSONArray(res.asString());
	//jrr.getJSONObject(0).getJSONArray("courses").get(1);  in jsonarray we are accessing jsonobjects and again jsonarray 
	
	//jsonarray can have jsonobjects and jsonobjects can have jsonarray vice versa is always possible
	
	//Approach 2 (by storing response into a variable we can perform more number of assertions)
	//@Test(priority=2)
	public void getResponse() {
		
		Response res=given()
		.contentType(ContentType.JSON)
		.when()
		.get("http://localhost:3000/store");    //get method will return response into response or object format format and hence we need to convert into string format to do validations
		
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");   //hear content type should be in double quotes
		
		String bookname =res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookname, "The Lord of the Rings");	
	}
	//scenario : get all the titles from the json and validate it
	//we can achieve this using JSONObject class which helps to traverse through the json 
	
	@Test(priority=3)
	public void getResponseBodyData() {
		
		Response res=given()
		.contentType(ContentType.JSON) //this contenttype should not be in double quotes when your specifying in given statement
		.when()
		.get("http://localhost:3000/store");
		
		JSONObject jo = new JSONObject(res.asString());  //converting entire response to json object type
		//Dynamically getting the size of an array
		//Scenario ://to print all the titles of the book
		for(int i=0;i<jo.getJSONArray("book").length();i++) {     //getjsonarray is a method which is used to get all the array objects present in JSON and find the total number of array objects in the JSON
			String booktitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();    //in jsonarray get the jsonobject and get variable name which needs to be validated and convert that to string format and store it in some variable
			System.out.println(booktitle);
		}
		
		//to check specific book title is present in json or not
		boolean status =false;    //using this statement to avoid looping continuously even after finding the exepcted value
		for(int i=0;i<jo.getJSONArray("book").length();i++) {
			String booktitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			if(booktitle.equals("The Lord of the Rings")) {
				status =true;       //as soon as u find the book come out of the loop and break the statement
				break;
			}
		}
		Assert.assertEquals(status, true);
		
		//Scenario ://sum all the prices in the json and compare it with the expected price
		double totalprice=0;
		for(int i=0;i<jo.getJSONArray("book").length();i++) {
         String price=jo.getJSONArray("book").getJSONObject(i).get("price").toString();
         totalprice=totalprice+Double.parseDouble(price);    //converting price from string format to double format
		}
		System.out.println("Total price of book is :"+totalprice);   //total price we got is 53.92
		Assert.assertEquals(totalprice, 53.92);
		
}}
