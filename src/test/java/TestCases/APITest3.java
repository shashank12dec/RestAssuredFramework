package TestCases;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APITest3 {
	
//	public static Properties prop = new Properties();
//	public static FileInputStream input ;
//	
//	Response resp;
//	static String body;
//	
//	@BeforeClass
//	public void dataSetup()
//	{
//		
//		//Specify base URI
//		RestAssured.baseURI = "http://216.10.245.166";
//		
//		//Request Object
//		RequestSpecification httprequest = RestAssured.given();
//		
//		// JSONObject is a class that represents a Simple JSON.
//		// We can add Key - Value pairs using the put method
//		JSONObject reqBody = new JSONObject();
//		reqBody.put("name", "Learn Appium Automation");
//		reqBody.put("isbn", "bchghd");
//		reqBody.put("aisle", "227899");
//		reqBody.put("author", "John");
//		
//		httprequest.header("Content-Type", "application/json");
//		httprequest.body(reqBody.toJSONString());
//		
//		//Response object
//		resp = httprequest.request(Method.POST, "/Library/Addbook.php");
//		
//		// print reponse in console
//		body = resp.getBody().asString();
//		System.out.println("Response Body for APITest is: " +body );
//		
//		
//		
//}	
//		// Validate Http status code
//		@Test
//		void checkStatus() {
//			int statusCode = resp.getStatusCode();
//			Assert.assertEquals(statusCode, 404);
//			System.out.println(statusCode);
//
//		}

	
	@Test
	public void postRequest()
	{
		HashMap reqBody = new HashMap();
		reqBody.put("name", "Learn Appium Automation");
		reqBody.put("isbn", "bchghd");
		reqBody.put("aisle", "227899");
		reqBody.put("author", "John");
		
		Response res = 
		
		given()
			.contentType("application/json")
			.body(reqBody)
		.when()
			.post("http://216.10.245.166/Library/Addbook.php")
		.then()
			.statusCode(404)
			.log().body()
			.extract().response();
			String strRes = res.asString();
////		System.out.println(strRes);
			Assert.assertEquals(strRes.contains("Add Book operation failed, looks like the book already exists"), true);
		
	}
}