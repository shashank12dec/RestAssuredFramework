package TestCases;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APITest2 {

	public static Properties prop = new Properties();
	public static FileInputStream input;
	
	Response resp;
	static String body;
	

	@BeforeClass
	public void requestSetup() throws IOException 
	{
			
		
//		 input = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
//		 prop.load(input);
		 
			//Specify base URI
			RestAssured.baseURI = prop.getProperty("BaseURI2");
			
			//Request Object
			RequestSpecification httprequest = RestAssured.given();
			
			httprequest.header("Content-Type", "application/json");
			
			
			//Response object
			resp = httprequest.request(Method.GET, "/Library/GetBook.php?AuthorName=Jhon");
			
			// print reponse in console
			body = resp.getBody().asString();
			System.out.println("Response Body for APITest is: " +body );
			
			
			
	}	
			// Validate Http status code
			@Test
			void checkStatus() {
				int statusCode = resp.getStatusCode();
				Assert.assertEquals(statusCode, 200);
				System.out.println(statusCode);
	
			}
	}


