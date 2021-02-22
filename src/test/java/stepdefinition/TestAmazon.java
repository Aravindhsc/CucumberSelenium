package stepdefinition;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.Generic;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.HomePage;
import pageObject.ProductsPage;

public class TestAmazon {

	WebDriver driver;
	Generic generic;
	HomePage home;
	ProductsPage product;
	public static ExtentReports reports;
	public static ExtentTest test;
	
	public WebDriver getdriver() {
		return driver;
	}
	
	@Given("Navigate to {string} application")
	public void navigate_to_application(String string) {
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
	    driver=new ChromeDriver();
	    driver.get("https://"+string);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    reports=new ExtentReports(System.getProperty("user.dir")+"\\Report\\Extenreport.html");
	}
	
	@When("As a Guest user search for Nikon Products")
	public void as_a_guest_user_search_for_nikon_products() throws IOException {
	   home=new HomePage(driver);
	   test=reports.startTest("Home");
	   try{
		   home.search_product("Nikon");
		   test.log(LogStatus.PASS, "User able to search for a product");
		}catch(Exception e) {
			test.log(LogStatus.FAIL, e);
			String screenshotpath=getScreenshot(driver,new Exception().getStackTrace()[0].getMethodName());
			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotpath));
		}
	}

	@When("Sort from High to Low price")
	public void sort_from_high_to_low_price() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		product=new ProductsPage(driver);
		test=reports.startTest("Product sorting");
		try {
		product.sort_High_to_low();
		test.log(LogStatus.PASS, "User sorted the products from High to low");
		}catch(Exception e) {
			test.log(LogStatus.FAIL, e);
			String screenshotpath=getScreenshot(driver,new Exception().getStackTrace()[0].getMethodName());
			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotpath));
		}
	    
	}

	@When("Results are sorted")
	public void results_are_sorted() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		product=new ProductsPage(driver);
		test=reports.startTest("Product Sorting");
		try{
			product.verify_sorted_product();
			test.log(LogStatus.PASS, "Sorting was successfull");
		}catch(Exception e) {
			test.log(LogStatus.FAIL, e);
			String screenshotpath=getScreenshot(driver,new Exception().getStackTrace()[0].getMethodName());
			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotpath));
		}
	    
	}

	@Then("Select second product from the result")
	public void select_second_product_from_the_result() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		product=new ProductsPage(driver);
		test=reports.startTest("Product Sorting");
		try {
		product.select_sorted_product();
		test.log(LogStatus.PASS, "User selected a second product after sorting");
		}catch(Exception e) {
			test.log(LogStatus.FAIL, e);
			String screenshotpath=getScreenshot(driver,new Exception().getStackTrace()[0].getMethodName());
			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotpath));
		}
	}

	@Then("Verify Product should be titled as {string}")
	public String verify_product_should_be_titled_as(String string) {
		String status="";
		product=new ProductsPage(driver);
		test=reports.startTest("Product Sorting");
		try {
			status=product.validate_sorted_product(string);
			System.out.println("In try");
			if(status.equalsIgnoreCase("pass")) {
				test.log(LogStatus.PASS, "Product details displayed was as expected");
			}
			else {
				test.log(LogStatus.FAIL, status);
				String screenshotpath=getScreenshot(driver,new Exception().getStackTrace()[0].getMethodName());
				test.log(LogStatus.FAIL, test.addScreenCapture(screenshotpath));
			}
			return status;
		}catch(Exception e) {
			System.out.println("In catch");
			test.log(LogStatus.FAIL, e);
			return status;
		}
		
		}
	@After
	public void tearDown() {
	    reports.flush();
	    driver.quit();
	}
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName=new SimpleDateFormat("yyyymmddhhmm").format(new Date());
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		String destination=System.getProperty("user.dir")+"/FailureScreenshots/"+screenshotName+dateName+".png";
		File finalDestination=new File(destination);
		FileUtils.copyFile(source,finalDestination);
		return destination;
	}

}
