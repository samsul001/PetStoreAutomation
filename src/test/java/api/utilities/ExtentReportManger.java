package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManger implements ITestListener{
	
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	String repName;
	public Logger log;
	
	
	public void onStart(ITestContext context) {
		log = LogManager.getLogger(this.getClass());
		log.info("***************** Reporting started *****************");
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-"+timeStamp+".html";
		
		htmlReporter = new ExtentSparkReporter(".\\Reports\\"+repName);
		
		htmlReporter.config().setDocumentTitle("RestAssured Automation Project");
		htmlReporter.config().setReportName("Pet Store Users API");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Application", "Pet Stores Users API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Samsul");
	}
	
	public void onTestSuccess(ITestResult result) {
		log.info("***************** Test Passed *****************");
		logger = extent.createTest(result.getName());
		logger.createNode(result.getName());
		logger.assignCategory(result.getMethod().getGroups());
		logger.log(Status.PASS, "Test Passed");
	}
	public void onTestFailure(ITestResult result) {
		log.debug("***************** Test Failed *****************");
		logger = extent.createTest(result.getName());
		logger.createNode(result.getName());
		logger.assignCategory(result.getMethod().getGroups());
		logger.log(Status.FAIL, "Test Failed");
		logger.log(Status.FAIL, result.getThrowable().getMessage());
	}
	public void onTestSkipped(ITestResult result) {
		log.debug("***************** Test Skipped *****************");
		logger = extent.createTest(result.getName());
		logger.createNode(result.getName());
		logger.assignCategory(result.getMethod().getGroups());
		logger.log(Status.SKIP, "Test Skipped");
		logger.log(Status.SKIP, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context) {
		log.debug("***************** Test Finished *****************");
		extent.flush();
	}
	
	

}
