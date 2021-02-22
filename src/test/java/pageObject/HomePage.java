package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class HomePage {
	static WebDriver driver;
	WebDriverWait wait;

	
	@FindBy(id="nav-logo-sprites")
	WebElement amazonLogo;
	
	@FindBy(id="twotabsearchtextbox")
	WebElement searchBar;
	
	@FindBy(id="nav-search-submit-button")
	WebElement searchButton;
	

	public HomePage(WebDriver indriver) {
		driver=indriver;	
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 20);
	}
	
	
	public void search_product(String searchValue) {
		wait.until(ExpectedConditions.visibilityOf(amazonLogo));
		searchBar.click();
		searchBar.sendKeys(searchValue);
		searchButton.click();
	}
}
