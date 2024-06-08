package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.CustomerInfo;
import abstractComponents.ReUsable;

public class Cart extends ReUsable
{
	WebDriver driver;
	public Cart(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//Locators:
	
	@FindBy(className="cart_item")
	WebElement inCart;
	
	@FindBy(name="checkout")
	WebElement checkout;
	
	//Actions:
	
	public String inbasket()
	{
		
		String basket =inCart.findElement(By.cssSelector(".inventory_item_name")).getText();
		return basket;
		
	}
	public CustomerInfo checkout()
	{
		
		checkout.click();
		CustomerInfo customerinfo = new CustomerInfo(driver);
		return customerinfo;
	}

}
