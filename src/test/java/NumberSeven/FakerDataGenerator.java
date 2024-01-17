package NumberSeven;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

//in order to create our own set of data we need to use faker library we need to download dependencies and add to pom.xml
//in faker library they have mentioned different set of data ex : name, business,animal,color etc using this you can create any fake data
//referal url : https://github.com/DiUS/java-faker
//below we have used name fakers and accesed methods present in it
public class FakerDataGenerator {

	@Test
	public void testGenerateDummyData() {
		
		Faker faker = new Faker();
		String fullname=faker.name().fullName();
		String firstname=faker.name().firstName();
		String lastname=faker.name().lastName();
		String username=faker.name().username();
		String password=faker.internet().password();
		String phoneno=faker.phoneNumber().cellPhone();
		String email=faker.internet().safeEmailAddress();   //internet faker and its method we accesed hear
		
		System.out.println("Full Name:"+fullname);
		System.out.println("First Name:"+firstname);
		System.out.println("Last Name:"+lastname);
		System.out.println("User Name:"+username);
		System.out.println("Password:"+password);
		System.out.println("Phone:"+phoneno);
		System.out.println("Email:"+email);		
	}
}
