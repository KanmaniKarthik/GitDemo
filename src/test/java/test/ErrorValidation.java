package test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import abstractComponents.Base;
import abstractComponents.Retry;
import pages.Home;
import pages.Login;

public class ErrorValidation extends Base
{
	@Test(dataProvider="getData", retryAnalyzer=Retry.class)
	public void loginFail(HashMap<String,String> input) throws IOException
	{
		Login loginpage = launchApplication();
		Home product = loginpage.loginActions(input.get("UserName"), input.get("Password"));
		product.addToCart(input.get("PN"));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = jsonToString(System.getProperty("user.dir")+
				"\\src\\test\\java\\abstractComponents\\data.json");
		return new Object[][] {{data.get(1)}};
	}
}
