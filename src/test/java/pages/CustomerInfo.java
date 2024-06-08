package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.OverView;
import abstractComponents.ReUsable;

public class CustomerInfo extends ReUsable
{
	WebDriver driver;
	public CustomerInfo(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//Locators:
	
	@FindBy(id="first-name")
	WebElement fName;
	
	@FindBy(id="last-name")
	WebElement lName;
	
	@FindBy(id="postal-code")
	WebElement postalCode;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement submit;
	
	//Actions:
	
	public OverView customerInfoActions(String FN, String LN, String PostalCode)
	{
		fName.sendKeys(FN);
		lName.sendKeys(LN);
		postalCode.sendKeys(PostalCode);
		submit.click();
		
		OverView overviewpage = new OverView(driver);
		return overviewpage;
	}
}
