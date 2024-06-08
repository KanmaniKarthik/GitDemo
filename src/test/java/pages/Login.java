package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.Home;
import abstractComponents.ReUsable;

public class Login extends ReUsable
{
	WebDriver driver;
	public Login(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//Locators:
	
	@FindBy(css="input#user-name")
	WebElement userName;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="login-button")
	WebElement loginButton;
	
	//Actions:
	
	public void landOn()
	{
		driver.get("https://www.saucedemo.com/");
	}
	
	public  Home loginActions(String UserName, String Password)
	{
		userName.sendKeys(UserName);
		password.sendKeys(Password);
		loginButton.click();
		
		Home product = new Home(driver);
		return product;
	}	
}
