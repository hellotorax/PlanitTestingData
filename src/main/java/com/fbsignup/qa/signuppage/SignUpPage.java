package com.fbsignup.qa.signuppage;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.fbsignup.qa.base.TestBase;


public class SignUpPage  extends TestBase {

	
	
	//Declaring the page factory- OR:

	@FindBy(name="firstname")
	WebElement ftname;
	

	@FindBy(name="lastname")
	WebElement ltname;

	@FindBy(name="reg_email__")
	WebElement mborEmail;


	@FindBy(name="reg_passwd__")
	WebElement newPwd;

	@FindBy(name="birthday_day")
	WebElement bdayDate;

	@FindBy(name="birthday_month")
	WebElement bdayMonth;

	@FindBy(name="birthday_year")
	WebElement bdayYear;
	
	
	String bfxPath="//input[@type='radio']/following-sibling::label[contains(text(),'";
	String afxPath = "')]";
	
	
	

	//Initializing the Page Objects:
	public SignUpPage(){
		PageFactory.initElements(driver, this);
	}

	public void signUp(String firstName, String lastName, String mobileOrEmail, String newPasswd, String day, String month, String year, String Gender){


		ftname.sendKeys(firstName);
		ltname.sendKeys(lastName);
		mborEmail.sendKeys(mobileOrEmail);
		newPwd.sendKeys(newPasswd);
		Select Day = new Select(bdayDate);
		Day.selectByVisibleText(day);	
		Select Month = new Select(bdayMonth);
		Month.selectByValue(month);
		Select Year = new Select(bdayYear);
		Year.selectByVisibleText(year);
		WebElement gender = driver.findElement(By.xpath(bfxPath+Gender+afxPath));
		gender.click();

	}

}
