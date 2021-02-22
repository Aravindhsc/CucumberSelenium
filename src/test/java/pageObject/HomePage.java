package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class HomePage {
	static WebDriver driver;			// Instantiating WebDriver
	WebDriverWait wait;					// Instantiating WebDriver wait

	
	@FindBy(id="nav-logo-sprites")			// Page Object Factory is implemented using @FindBy annotation to store locator values
	WebElement amazonLogo;
	
	@FindBy(id="twotabsearchtextbox")
	WebElement searchBar;
	
	@FindBy(id="nav-search-submit-button")
	WebElement searchButton;
	

	public HomePage(WebDriver indriver) {			// Constructor is created with Webdriver passed as parameter
		driver=indriver;							// Assigning to class variable driver
		PageFactory.initElements(driver, this);		// Initialize web elements
		wait=new WebDriverWait(driver, 20);			// Creating object for explicit wait time for 10 seconds
	}
	
	
	public void search_product(String searchValue) {
		wait.until(ExpectedConditions.visibilityOf(amazonLogo));			//Explicit wait time for 10 seconds
		searchBar.click();													//  click command to click the element
		searchBar.sendKeys(searchValue);									// Value is keyed in to search bar		
		searchButton.click();	
	}
}
