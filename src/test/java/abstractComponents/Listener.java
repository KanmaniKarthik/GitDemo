package abstractComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import abstractComponents.Base;

public class Listener extends Base implements ITestListener
{

	ExtentReports extent = Base.exrep();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		
		extentTest.get().log(Status.PASS, "Test Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		
		extentTest.get().log(Status.FAIL, "Test failed");
		extentTest.get().fail(result.getThrowable());
		//test.fail(result.getThrowable());  //(for series test) prints the error message in the report
		
		try
		{
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} 
		catch (Exception e1)
		{
			
			e1.printStackTrace();
		}

		
		String filePath=null;
		try
		{
			filePath = screenShot(result.getMethod().getMethodName(), driver);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	    extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		
		extent.flush();

	}
	
}
