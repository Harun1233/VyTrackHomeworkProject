package com.vytrack.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.vytrack.utilites.BrowserUtils;
import com.vytrack.utilites.ConfigurationReader;
import com.vytrack.utilites.Driver;
import com.vytrack.utilites.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestBase {

    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;
    //this class is used for starting and building reports
    protected ExtentReports report;
    //this class is used for to create HTMl report file
    protected ExtentHtmlReporter htmlReporter;
    //this will define a test, enables adding logs, authors, test steps
    protected ExtentTest extentLogger;

    @BeforeTest
    public void setUpTest(){
        //initialize the class
        report=new ExtentReports();

        //create a report path
        String projectPath=System.getProperty("user.dir");
        String path=projectPath+"/test-output/report.html";

        //initialize the html reporter with the report path
        htmlReporter=new ExtentHtmlReporter(path);

        //attach the html report to report object
        report.attachReporter(htmlReporter);

        //title in report
        htmlReporter.config().setReportName("Vytrack Smoke Test");

        //set environment info
        report.setSystemInfo("Environment","QA");
        report.setSystemInfo("Browser", ConfigurationReader.get("browser"));
        report.setSystemInfo("OS",System.getProperty("os.name"));


    }
    @DataProvider
    public Object[][] storeManagerData(){
        String worksheet=ConfigurationReader.get("excelWorkSheet");
        String excelFileName=ConfigurationReader.get("excelFileName");
        ExcelUtil strMngr=new ExcelUtil("src/test/resources/"+excelFileName,worksheet);

        String[][] dataArray = strMngr.getDataArray();

        return dataArray;
    }


    @AfterTest
    public void tearsDownTest(){
        //this is when the report is actually created
        report.flush();

    }

    @BeforeMethod
    public void setUp(){
        driver= Driver.get();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        actions=new Actions(driver);
        wait=new WebDriverWait(driver,10);
        driver.get(ConfigurationReader.get("url"));
    }

    //ItestResult class descrives the result of a test in TestNG
    @AfterMethod
    public void tearsDown(ITestResult result) throws InterruptedException, IOException {
        //if test fails
        if(result.getStatus()==ITestResult.FAILURE){
            //record the name of failed test case
            extentLogger.fail(result.getName());

            //take the screenShot and return the location of screenSHot
            Thread.sleep(5000);
            String screenShotPath= BrowserUtils.getScreenshot(result.getName());

            //add your SS to your report

            extentLogger.addScreenCaptureFromPath(screenShotPath);

            // capture the exception and put it inside the report
            extentLogger.fail(result.getThrowable());

        }


        Thread.sleep(2000);
        Driver.closeDriver();
    }
}

