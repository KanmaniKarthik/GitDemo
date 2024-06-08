package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.Confirmation;
import abstractComponents.ReUsable;

public class OverView extends ReUsable
{
	WebDriver driver;
	public OverView(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//Locators:
	
	@FindBy(css=".summary_info")
	WebElement summary;
	
	@FindBy(id="finish")
	WebElement finish;
	
	//Actions:
	
	public Confirmation overViewActions()
	{
		String summary =driver.findElement(By.cssSelector(".summary_info")).getText().split("Cancel")[0].trim();
		System.out.println(summary);
		finish.click();
		
		Confirmation confirmationpage = new Confirmation(driver);
		return confirmationpage;
	}
}
