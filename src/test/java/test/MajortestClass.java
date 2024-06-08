package test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import abstractComponents.Base;
import pages.Cart;
import pages.Confirmation;
import pages.CustomerInfo;
import pages.Home;
import pages.Login;
import pages.OverView;

public class MajortestClass extends Base
{
	@Test(dataProvider="getData")
	public void purchase(HashMap<String,String> input) throws IOException
	{
		Login loginpage = launchApplication();
		Home product =loginpage.loginActions(input.get("UserName"), input.get("Password"));
		Cart cartpage = product.addToCart(input.get("PN"));
		String basket = cartpage.inbasket();
		Assert.assertEquals(input.get("PN"), basket);
		System.out.println(basket);
		CustomerInfo customerinfo = cartpage.checkout();	
		OverView overviewpage =customerinfo.customerInfoActions(input.get("FN"), input.get("LN"), input.get("PostalCode"));
		Confirmation confirmationpage = overviewpage.overViewActions();
		String Message = confirmationpage.confirmation();
		Assert.assertEquals("Thank you for your order!", Message);
		System.out.println(Message);
		product.otherFunctions();
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = jsonToString(System.getProperty("user.dir")+
				"\\src\\test\\java\\abstractComponents\\data.json");
		return new Object[][] {{data.get(0)}};
	}
}
