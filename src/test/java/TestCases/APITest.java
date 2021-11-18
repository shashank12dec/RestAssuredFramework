package TestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APITest {

	public static Properties prop = new Properties();
	public static FileInputStream input;
	
	Response resp;
	static String body;

	@BeforeClass
	public void requestSetup() throws IOException 
	{
			
		
		 input = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		 prop.load(input);
		 
			//Specify base URI
			RestAssured.baseURI = prop.getProperty("BaseURI");
			
			//Request Object
			RequestSpecification httprequest = RestAssured.given();
			
			httprequest.header("Content-Type", "application/json");
			
			
			//Response object
			resp = httprequest.request(Method.GET, "/apitest");
			
			// print reponse in console
			body = resp.getBody().asString();
	//		System.out.println("Response Body for APITest is: " +responseBody );
			
			
			
	}	
			// Validate Http status code
			@Test
			void checkStatus() {
				int statusCode = resp.getStatusCode();
				Assert.assertEquals(statusCode, 200);
	
			}
			
			//Validate Response Header 
			@Test
			void responseHeader () {
				
				 // Header named Content-Type
				String contentType = resp.header("content-type");
				Assert.assertEquals(contentType , "application/json; charset=UTF-8");
				//  Header named Server
				String serverType = resp.header("server");
				Assert.assertEquals(serverType , "Google Frontend");
			}
			
			
			@Test
			void checkResponseBody() {
				
				List<Map<String,Object>> empDetails = resp.jsonPath().getList("employeeData");
				
			//Validate response Status Code	
				int responseStatusCode = resp.jsonPath().get("status");
				Assert.assertEquals(responseStatusCode,200);
				
			//Validate response Age
				int age = (Integer) empDetails.get(0).get("age");
				Assert.assertEquals(age,25, "Getting wrong age");

			
			//Validate response Role
				String role = (String) empDetails.get(0).get("role");
				Assert.assertEquals(role,prop.getProperty("Role"));
			
			//Validate response DOB
			
				String dob = (String) empDetails.get(0).get("dob");
				Assert.assertEquals(dob,prop.getProperty("DOB"));
			
			//Validate response Message
			
				String message = resp.jsonPath().get("message");
				Assert.assertEquals(message,prop.getProperty("Message"));
			}
			
			//Validate response Company
			@Test
			void checkCompany() {
				
				List<Map<String,String>> empDetails = resp.jsonPath().getList("employeeData");
				String company = empDetails.get(0).get("company");
				Assert.assertEquals(company,"ABC Infotech", "Company Name is not matching ==> ");
			}

}
