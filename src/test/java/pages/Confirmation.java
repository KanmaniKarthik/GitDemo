package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import abstractComponents.ReUsable;

public class Confirmation extends ReUsable
{
     WebDriver driver;
     public Confirmation(WebDriver driver)
     {
    	 super(driver);
    	 this.driver=driver;
    	 PageFactory.initElements(driver,this);
     }
     
     //Locators:
     
     @FindBy(css=".complete-header")
 	WebElement message;
 	
 	@FindBy(xpath="//*[text()='Back Home']")
 	WebElement backToHome;

 	//Actions:
 	
 	public String confirmation()
 	{
 		
 		String Message = message.getText();
 		
 		backToHome.click();
 		return Message;
 	}
}
