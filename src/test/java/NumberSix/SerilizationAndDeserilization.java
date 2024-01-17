package NumberSix;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import NumberTwo.Pojo_Postrequest;

//pojo-json : Serilization
//Json-Pojo:Deserilization

public class SerilizationAndDeserilization {

	//Pojo ---- Json (Serilization)
	//@Test
	public void convertPojo2Json() throws JsonProcessingException {
		
		//created java object using pojo class
		student stupojo = new student();  //pojo
		
		stupojo.setName("Akhil");
		stupojo.setLocation("Ballari");
		stupojo.setPhone("23455");
		String CoursesArr[]= {"C","C++"};
		stupojo.setCourses(CoursesArr);
		
		//convert java object to json object (Serilization)
		ObjectMapper objMapper = new ObjectMapper();  //import objectmapper class from com.fasterxml.jackson.databind and not from io.restassured
		String jsondata=objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stupojo);  //this line converts java object pojo to json format
		System.out.println(jsondata);
	}
	
	//Json ---- Pojo (Deserilization)
		@Test
		public void convertJson2Pojo() throws JsonProcessingException {
	
			String jsondata ="{\r\n"
					+ "  \"name\" : \"Akhil\",\r\n"
					+ "  \"location\" : \"Ballari\",\r\n"
					+ "  \"phone\" : \"23455\",\r\n"
					+ "  \"courses\" : [ \"C\", \"C++\" ]\r\n"
					+ "}";
			
			//convert json data to Java object (Serilization)
			ObjectMapper objMapper = new ObjectMapper(); 
			student stupojo=objMapper.readValue(jsondata, student.class);   //this line converts json format to java object pojo 
			System.out.println("Name:"+stupojo.getName());
			System.out.println("Location:"+stupojo.getLocation());
			System.out.println("phone:"+stupojo.getPhone());
			System.out.println("Courses:"+stupojo.getCourses()[0]);
			System.out.println("Courses:"+stupojo.getCourses()[1]);
			
	
		}}
