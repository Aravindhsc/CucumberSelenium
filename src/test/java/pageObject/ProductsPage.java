package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import common.Generic;

import org.junit.Assert;

public class ProductsPage{
	WebDriver driver;
	WebDriverWait wait;

	
	
	@FindBy(xpath="//*[@class='a-dropdown-prompt']")
	WebElement sortButton;
	
	@FindBy(xpath="//a[text()='Price: High to Low']")
	WebElement sortDesc;
	
	@FindBy(xpath="((//*[@class='s-main-slot s-result-list s-search-results sg-row']/div)[2]//a)[2]")
	WebElement searchResult2;
	
	@FindBy(id="nav-search-submit-button")
	WebElement searchButton;
	
	@FindBy(xpath="//*[@id='sorts']/span[1]")
	WebElement sortButto1n;
	
	@FindBy(id="productTitle")
	WebElement productTitle;
	
	public ProductsPage(WebDriver in_driver) {
		driver=in_driver;
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver,10);
	}
	
	public void sort_High_to_low() {
		wait.until(ExpectedConditions.visibilityOf(sortButton));
		sortButton.click();
		wait.until(ExpectedConditions.visibilityOf(sortDesc));
		sortDesc.click();
		
		
	}
	
	public void verify_sorted_product() {
		wait.until(ExpectedConditions.visibilityOf(sortButton));
		String Actual_text=sortButton.getText();
		System.out.println(Actual_text);
		Assert.assertEquals("Price: High to Low", Actual_text);
		
	}
	
	public void select_sorted_product() {
		wait.until(ExpectedConditions.visibilityOf(searchResult2));
		searchResult2.click();
		
	}
	
	public String validate_sorted_product(String string) {
		wait.until(ExpectedConditions.visibilityOf(productTitle));
		String Expected_Title=string;
		String Actual_Title = productTitle.getText();
		System.out.println(Actual_Title);
		try {
			Assert.assertEquals(Expected_Title, Actual_Title.contains(Expected_Title));
			return "Pass";
		}catch (AssertionError a) {
			System.out.println(a.toString());
			return "Expected :"+Expected_Title+" ,But got :"+Actual_Title;	
		}
	}
}
