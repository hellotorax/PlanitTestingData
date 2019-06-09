package com.fbsignup.qa.tests;

import com.fbsignup.qa.base.TestBase;
import com.fbsignup.qa.signuppage.SignUpPage;
import com.fbsignup.qa.util.TestUtil;



import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class FaceBookSignUpTest extends TestBase {


	SignUpPage signUpPage;
	TestUtil testutil;
	String sheetName = "signUp";

	public FaceBookSignUpTest(){

		super();

	}

	@BeforeMethod
	public void setUp(){
		initialization();
		signUpPage  = new SignUpPage();	
	}


	@DataProvider
	public Object[][] getSignUpData(){

		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}


	//Test for the happy path Scenarios
	@SuppressWarnings("static-access")
	@Test (priority=1,dataProvider="getSignUpData")
	public void signUpFaceBook(String firstName, String lastName, String mobileOrEmail, String newPasswd, String day, String month, String year, String gender) throws IOException{
		signUpPage.signUp(firstName, lastName, mobileOrEmail, newPasswd, day, month, year,gender);
		driver.findElement(By.xpath("//button[@id='u_0_19' and @type='submit']")).click();
		testutil.takeScreenshotAtEndOfTest();
	}

	/*Test for -ve Scenarios:
	 * 
	 * To Validate different -ve cases, we have to use the assertion method: 
	 * Assuming we have the set of error descriptions in the page for each of the fields
	 * we can write below test
	 * Using a sample condition below
	 * 
	 * 
	 */


	@SuppressWarnings("static-access")
	@Test(priority=2,dataProvider="getSignUpData")
	public void signUpFaceBookInvalidData(String firstName, String lastName, String mobileOrEmail, String newPasswd, String day, String month, String year, String gender) throws IOException{
		signUpPage.signUp(firstName, lastName, mobileOrEmail, newPasswd, day, month, year, gender);
		driver.findElement(By.xpath("//button[@id='u_0_19' and @type='submit']")).click();

		try

		{

			String errorCodeStr;
			//get the xpath of the error code:

			errorCodeStr=driver.findElement(By.xpath("")).getText();
			//Compare the error descriptions with one of the below error descriptions(Samples are Below)


			if (errorCodeStr.equalsIgnoreCase("Please enter a valid mobile number or email address"))
			{

				System.out.println("-VE Scenario Checked");

			}
		}


		catch(Exception e)
		{
			System.out.println("Error Occured" + e.getMessage());
		}
		
		testutil.takeScreenshotAtEndOfTest();
	}
	
	

	@AfterMethod
	public void tearDown(){
		driver.quit();

	}
}
