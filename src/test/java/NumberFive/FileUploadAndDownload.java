package NumberFive;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.io.File;

import org.testng.annotations.Test;

//prereq read from notes first
public class FileUploadAndDownload {

	@Test(priority=1)
	public void singleFileUpload() {
		
		File myfile = new File("C:\\Users\\AS086960\\OneDrive - Cerner Corporation\\Documents\\Resume.txt");
		given()
		
		//these below 2 statements are must and should we shd write whenever we upload a file which goes with the post request
		.multiPart("file",myfile)     //represents form data which we pass manually in postman key value pair
		.contentType("multipart/form-data")    //content type as well we need to specify as given hear compulsory we need to specify as it written hear for file upload and download
		
		.when()
		.post("http://localhost:8080/uploadFile")
		.then()
		.statusCode(200)
		.body("filename",equalTo("Resume.txt"))
		.log().all();	
	}
	@Test
	public void multipleFileUpload() {
		File myfile1 = new File("C:\\Users\\AS086960\\OneDrive - Cerner Corporation\\Documents\\Resume1.txt");
		File myfile2 = new File("C:\\Users\\AS086960\\OneDrive - Cerner Corporation\\Documents\\Resume2.txt");
		given()
		
		//these below 2 statements are must and should we shd write whenever we upload a file which goes with the post request
		.multiPart("files",myfile1)     //represents form data which we pass manually in postman key value pair
		.multiPart("files",myfile2)
		.contentType("multipart/form-data")    //content type as well we need to specify 
		
		.when()
		.post("http://localhost:8080/uploadMultipleFiles")
		.then()
		.statusCode(200)
		.body("[0].filename",equalTo("Resume1.txt"))
		.body("[1].filename",equalTo("Resume2.txt"));
	}
	@Test
	public void fileDownload() {
		
		given()
		.when()
		.get()  //hear we need to pass fileuploaded path 
		.then()
		.statusCode(200);
	}
}
