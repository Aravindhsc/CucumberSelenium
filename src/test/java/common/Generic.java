package common;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pageObject.HomePage;
import pageObject.ProductsPage;

public class Generic {
	WebDriver driver;
	HomePage home;
	ProductsPage product;

	public File file;
	
	
	public Generic(WebDriver in_driver) {
		driver=in_driver;
		
	}
	
	public void setup(String url) {
			
	}
}
