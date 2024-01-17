package NumberFive;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
//@Test(priority=1)
public class ParsingXMLResponse {

	public void testXMLResponse() {
		//Approach 1
		given()
		.when()
		.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
		.statusCode(200)
		.header("Content-Type","application/xml; charset=utf-8")
		.body("TravelerinformationResponse.page", equalTo("1"))
		.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));	
	}
	//Approach 2
	@Test(priority=2)
	public void testXMLBodyResponse() {
		//Approach 1
		Response res=given()
		.when()
		.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.header("Content-Type"),"application/xml; charset=utf-8");
		String pageno=res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageno, "1");
		String travellername=res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
		Assert.assertEquals(travellername, "Developer");
		
		//Scenario : find the total number of travellers and compare with the expected number of travellers
		//To acheive this we use XmlPath class for XML, and we use Objectclass for JSON
		
		XmlPath xmlobj = new XmlPath(res.asString());
		List<String> travellers=xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");
		Assert.assertEquals(travellers.size(), 10);
		
		//Scenario: Verify particular name or email present in XMLresponse or not
		boolean status =false;
		List<String> traveller_name=xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		for(String travellername1:traveller_name) {
		if(travellername1.equals("Developer"));{
			status=true;
			break;
		}	
		}
		Assert.assertEquals(status, true);
	}
}
