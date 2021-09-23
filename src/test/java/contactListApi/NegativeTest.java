package contactListApi;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

public class NegativeTest {
	@Test(enabled=true,description="Getting Contact which does not exist")
	public void getSpecificContact() {
		System.out.println("Getting Contact");
					given()
					.when()
						.get("http://3.13.86.142:3000/contacts/5")
					.then()
						.log()			//Print
						.body()
						.statusCode(404);   // Assertion
	}	

	@SuppressWarnings("unchecked")
	@Test(enabled=false,description="Add Contact with Missing Email ID")
	public void addContactWithMissingEmailID() {
		System.out.println("Creating Contact");
		JSONObject data=new JSONObject();
		JSONObject emp=new JSONObject();
		JSONObject loc=new JSONObject();
		emp.put("jobTitle", "QA");
		emp.put("company", "LTI");
		loc.put("city", "Mumbai");
		loc.put("country", "India");
		
		data.put("firstName", "Amy");
		data.put("lastName", "Sharma");
		//data.put("email", "mpremchand99@gmail.com");
		data.put("location", loc);
		data.put("employer", emp);
		
			String err=	 given()
						.header("Content-Type","application/json")
						.body(data.toJSONString())
					.when()
						.post("http://3.13.86.142:3000/contacts")
					.then()
						.log()			//Print
						.body()
						.statusCode(400)   // Assertion
						.extract().path("err");
				Assert.assertEquals("Contacts validation failed: email: Email is required", err);
	}

	@SuppressWarnings("unchecked")
	@Test(enabled=true,description="Negative Test with too many Character")
	public void addContactWithMoreSize() {
		System.out.println("Creating Contact");
		JSONObject data=new JSONObject();
		JSONObject emp=new JSONObject();
		JSONObject loc=new JSONObject();
		emp.put("jobTitle", "QA");
		emp.put("company", "LTI");
		loc.put("city", "Mumbai");
		loc.put("country", "India");
		
		data.put("firstName", "MohitMohitMohitMohitMohitMohitMohitMohitMohitMohit");
		data.put("lastName", "Sharma");
		data.put("email", "mpremchand99@gmail.com");
		data.put("location", loc);
		data.put("employer", emp);
		
			String err=	 given()
						.header("Content-Type","application/json")
						.body(data.toJSONString())
					.when()
						.post("http://3.13.86.142:3000/contacts")
					.then()
						.log()			//Print
						.body()
						.statusCode(400)   // Assertion
						.extract().path("err");
				Assert.assertEquals(true, err.contains("is longer than the maximum allowed length"));
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Test(enabled=true,description="Adding contact with invalid first name")
	public void addNameWithInvalidFirstName() {
		System.out.println("Creating Contact");
		JSONObject data=new JSONObject();
		JSONObject emp=new JSONObject();
		JSONObject loc=new JSONObject();
		emp.put("jobTitle", "QA");
		emp.put("company", "LTI");
		loc.put("city", "Mumbai");
		loc.put("country", "India");
		
		data.put("firstName", "Mohit466");
		data.put("lastName", "Sharma");
		data.put("email", "mpremchand99@gmail.com");
		data.put("location", loc);
		data.put("employer", emp);
		
			String ss=	 given()
						.header("Content-Type","application/json")
						.body(data.toJSONString())
					.when()
						.post("http://3.13.86.142:3000/contacts")
					.then()
						.log()			//Print
						.body()
						.statusCode(400)   // Assertion
						.extract().path("err");
				Assert.assertTrue(ss.contains("Contacts validation failed"));
	}
	
	@SuppressWarnings("unchecked")
	@Test(enabled=true,description="Adding contact with invalid character")
	public void addInvalidCharacter() {
		System.out.println("Creating Contact");
		JSONObject data=new JSONObject();
		JSONObject emp=new JSONObject();
		JSONObject loc=new JSONObject();
		emp.put("jobTitle", "QA");
		emp.put("company", "LTI");
		loc.put("city", "Mumbai");
		loc.put("country", "India");
		
		data.put("firstName", "Mohit");
		data.put("lastName", "Sharma");
		data.put("email", "mpremchand99gmailcom");
		data.put("location", loc);
		data.put("employer", emp);
		
			String sh=	 given()
						.header("Content-Type","application/json")
						.body(data.toJSONString())
					.when()
						.post("http://3.13.86.142:3000/contacts")
					.then()
						.log()			//Print
						.body()
						.statusCode(400)   // Assertion
						.extract().path("err");
				Assert.assertTrue(sh.contains("Contacts validation failed"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}