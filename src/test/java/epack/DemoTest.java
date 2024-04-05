package epack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoTest {
	
	WebDriver driver;
	ExtentReports extent;
	
	@BeforeMethod
	public void configuration() {
		
		String reportPath = System.getProperty("user.dir")+"\\reports\\index.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		
		reporter.config().setReportName("Omayo Test Report");
		reporter.config().setDocumentTitle("Omayo Test Report");
		
		extent = new ExtentReports();
		
		extent.attachReporter(reporter);
		extent.setSystemInfo("Operating System", "Windows 10");
		extent.setSystemInfo("Tested By", "Sanjay Acharya");
		
	}
	
	@Test
	public void testOne() {
		
		ExtentTest eTest = extent.createTest("Test One");
		
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		eTest.info("Chrome Browser Launched");
		driver.get("https://omayo.blogspot.com/");
		eTest.info("Navigated to Omamyo Home Page");
		String actualText = driver.findElement(By.id("pah")).getText();
		
//		eTest.fail("Test One Failed"); // This is for failing the extent report
		Assert.assertEquals("PracticeAutomationHere", actualText);
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.close();
		extent.flush();
	}

}
