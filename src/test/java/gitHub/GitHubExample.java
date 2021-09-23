package gitHub;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class GitHubExample {
  @Test(enabled=false, description="get all repository for Authentic User")
  public void getAllRepositories() {
	  given()
	  .auth()
	  .oauth2("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")
	  .when()
	  .get("https://api.github.com/user/repos")
	  .then()
.log()
.body()
.statusCode(200);
	   }
  
  
  
  
  @SuppressWarnings("unchecked")
@Test(enabled=true,description="get all repository for Authentic User")
  public void createRepository() {
	 JSONObject para=new JSONObject();
	 para.put("name", "RestAssuredCreatedMe");	 
	 para.put("description", "Sample for Post Request");  
	 para.put("homepage", "application/json");
	  
	  given()
	  .auth()
	  .oauth2("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")
	  .header("Content-Type","application/json")
	  .body(para.toJSONString())
	  .when()
	  .post("https://api.github.com/user/repos")
	  .then()
.log()
.body()
.statusCode(201);
	   }
 
}
