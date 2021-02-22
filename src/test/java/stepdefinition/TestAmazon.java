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
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.HomePage;
import pageObject.ProductsPage;

public class TestAmazon {

	WebDriver driver;			// Instantiating WebDriver
	HomePage home;				
	ProductsPage product;
	public static ExtentReports reports;
	public static ExtentTest test;
	public String status="";
	
	public WebDriver getdriver() {		// Constructor returns webdriver to re use same webdriver in other pages in PageObject package
		return driver;
	}
	
	@Given("Navigate to {string} application")		// Setup
	public void navigate_to_application(String string) {
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");		// Chrome driver executable
	    driver=new ChromeDriver();														// object creation for chrome driver
	    driver.get("https://"+string);													// To navigate to url
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);				// Implicit wait time for 10 seconds to allow url to load
	    driver.manage().window().maximize();											// To Maximize the browser
	    reports=new ExtentReports(System.getProperty("user.dir")+"\\Report\\Extenreport.html");  // Directory for creating report
	}
	
	@When("As a Guest user search for Nikon Products")			// Annotations with feature file step
	public void as_a_guest_user_search_for_nikon_products() throws IOException {
	   home=new HomePage(driver);								// Object for HomePage is created by passing webdriver
	   test=reports.startTest("Home");							// Toggles the start of report and add logs 				
	   try{
		   home.search_product("Nikon");						// method is called with the object created
		   test.log(LogStatus.PASS, "User able to search for a product");	// On success logs are appended with message in to report
		}catch(Exception e) {
			test.log(LogStatus.FAIL, e);									// On failure logs are appended along with screenshot in report.
			String screenshotpath=getScreenshot(driver,new Exception().getStackTrace()[0].getMethodName());		// screenshot path
			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotpath));
		}
	}

	@When("Sort from High to Low price")
	public void sort_from_high_to_low_price() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		product=new ProductsPage(driver);			// Object for product page
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
		test=reports.startTest("Verfifying Sorting");
		status="";
		try{
			status=product.verify_sorted_product();
			if(status.equalsIgnoreCase("pass")) {
				test.log(LogStatus.PASS, "Sorting was successfull");
			}
			else {
				test.log(LogStatus.FAIL, status);
				String screenshotpath=getScreenshot(driver,new Exception().getStackTrace()[0].getMethodName());
				test.log(LogStatus.FAIL, test.addScreenCapture(screenshotpath));
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL, e);
			
		}
	    
	}

	@Then("Select second product from the result")
	public void select_second_product_from_the_result() throws IOException {
		product=new ProductsPage(driver);
		test=reports.startTest("Selecting Product");
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
		status="";
		product=new ProductsPage(driver);
		test=reports.startTest("Verification of Product Details");
		try {
			status=product.validate_sorted_product(string);			// storing the result of method executed
			System.out.println("In try");
			if(status.equalsIgnoreCase("pass")) {					// Validates Assertion result
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
	    reports.flush();		// Ends the report
	    driver.quit();			// close the chromedriver
	}
	
	
	
		/*	Method to capture screenshot and appends current timestamp with screenshot name passed as parameter 
		 	and stores in .png format in FailureScreesnhot directory */
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
