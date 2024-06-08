package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.Cart;
import abstractComponents.ReUsable;

public class Home extends ReUsable
{
	WebDriver driver;
	public Home(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//Locators:
	
By holdon =By.cssSelector(".inventory_item");
	
	@FindBy(css=".inventory_item")
	List<WebElement> products;
	
	@FindBy(className="shopping_cart_link")
	WebElement cart;
	
	@FindBy(css=".product_sort_container")
	WebElement staticDropDown;
	
	@FindBy(xpath="//*[text()='Open Menu']")
	WebElement menu;
	
	@FindBy(css=".bm-cross-button")
	WebElement close;
	
	@FindBy(id="inventory_sidebar_link")
	WebElement inventory;
	
	@FindBy(xpath="//*[text()='Products']")
	WebElement text;
	
	@FindBy(id="about_sidebar_link")
	WebElement about;
	
	@FindBy(id="logout_sidebar_link")
	WebElement logOut;
	
	//Actions:
	
	public Cart addToCart(String PN)
	{
		waitToAppear(holdon);
		System.out.println("Successfully loged in!!");

		WebElement item = products.stream().filter(product->
                    product.findElement(By.className("inventory_item_name")).getText().equals(PN)).findFirst().orElse(null);
        item.findElement(By.tagName("button")).click();
        
        cart.click();
        
        Cart cartpage = new Cart(driver);
        return cartpage;       		
	}
	
	public void otherFunctions()
	{
		
		/* Select dropdown = new Select(staticDropDown);
		   dropdown.selectByIndex(2);
		   dropdown.getFirstSelectedOption().click();  */   //stale error
		
		menu.click();
		inventory.click();
		System.out.println(text.isDisplayed());
		close.click();
		
		menu.click();
		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
		about.sendKeys(selectLinkOpeninNewTab);
		close.click();
		
		menu.click();
		logOut.click();
		System.out.println("Loged out!!");
	}

	
}