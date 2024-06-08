package abstractComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.Login;

public class Base
{
	public WebDriver driver;
	ExtentReports extent = Base.exrep();

	public WebDriver driverIntializer() throws IOException
	{
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\abstractComponents\\GlobalData.properties");
			prop.load(fis);
			
			String BrowserName = prop.getProperty("browser");
				
			if(BrowserName.equals("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			else if(BrowserName.equals("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
			else if(BrowserName.contains("edge"))
			{
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().maximize();
			
			return driver;
	}
	
	public static ExtentReports exrep()
	{
		String path = System.getProperty("user.dir") + "//TestReports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);        //helper class
		reporter.config().setReportName("Web Automation Results");  
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports();              //main class
		extent.attachReporter(reporter);              //attach the object of helper class, now it has the knowledge of that class
		extent.setSystemInfo("Tester", "KANMANI");
		return extent;
	}
	
	public List<HashMap<String, String>> jsonToString(String filepath) throws IOException
	{
		String jsonContent = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>> () {});
		return data;
	}

	//@BeforeMethod(alwaysRun=true)
	public Login launchApplication() throws IOException
	{
		driver = driverIntializer();
		Login loginpage = new Login(driver);
		loginpage.landOn();
		return loginpage;
	}
	
	public String screenShot(String testCaseName, WebDriver driver) throws IOException
	{
	    TakesScreenshot pic = (TakesScreenshot)driver;
		File source = pic.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//" + testCaseName +".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//" + testCaseName +".png"; 
		
/*		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotBase64 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		return screenshotBase64;    */
	}
	
	@AfterMethod(alwaysRun=true)
	public void shutDown()
	{
		driver.quit();
		extent.flush();
	}
}
