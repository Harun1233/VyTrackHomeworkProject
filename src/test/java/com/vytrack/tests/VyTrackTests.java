package com.vytrack.tests;

import com.vytrack.pages.CalendarEventsPage;
import com.vytrack.pages.LogInPage;
import com.vytrack.utilites.ConfigurationReader;
import com.vytrack.utilites.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

public class VyTrackTests extends TestBase {

    LogInPage logInPage;
    CalendarEventsPage calendarEventsPage;

    @Test(dataProvider = "storeManagerData")
    public void subtitleTest(String username, String password) {
        extentLogger = report.createTest("VyTrack Homework Tests- Subtitle Verification");


        logInPage = new LogInPage();
        logInPage.login(username, password);
        extentLogger.info("Log in to app as a store manager ");
        extentLogger.info("Test datas: username: " + username + " password: " + password);

        calendarEventsPage = new CalendarEventsPage();


        calendarEventsPage.navigateToModule(ConfigurationReader.get("tabName"), ConfigurationReader.get("moduleName"));
        extentLogger.info("Navigate to Calendar Events page under Activities tab");

        extentLogger.info("Verify that the 'Options' has displayed at page subtitle");
        Assert.assertTrue(calendarEventsPage.optionsButton.isDisplayed(), "Verify that 'Options' button has been displayed on page subtitle");

        extentLogger.pass("subtitleTest has been passed");


    }

    @Test(dataProvider = "storeManagerData")
    public void pageNumberTest(String username, String password) {
        extentLogger = report.createTest("VyTrack Homework Tests- PageNumber Verification");


        logInPage = new LogInPage();
        logInPage.login(username, password);
        extentLogger.info("Log in to app as a store manager ");
        extentLogger.info("Test datas: username: " + username + " password: " + password);

        calendarEventsPage = new CalendarEventsPage();


        calendarEventsPage.navigateToModule(ConfigurationReader.get("tabName"), ConfigurationReader.get("moduleName"));
        extentLogger.info("Navigate to Calendar Events page under Activities tab");


        String actualValue = calendarEventsPage.pageNumber.getAttribute("value");
        String expectedValue = "1";

        Assert.assertEquals(actualValue, expectedValue, "Verify that the page number on Calendar Events page is 1 as default");
        extentLogger.info("Verify that the default page number at the page is 1");

        extentLogger.pass("pageNumberTest is passed");


    }

    @Test(dataProvider = "storeManagerData")
    public void viewPerPageNumberTest(String username, String password) {
        extentLogger = report.createTest("VyTrack Homework Tests- PerPageNumber Verification");
        logInPage = new LogInPage();
        logInPage.login(username, password);
        extentLogger.info("Log in to app as a store manager ");
        extentLogger.info("Test datas: username: " + username + " password: " + password);

        calendarEventsPage = new CalendarEventsPage();


        calendarEventsPage.navigateToModule(ConfigurationReader.get("tabName"), ConfigurationReader.get("moduleName"));
        extentLogger.info("Navigate to Calendar Events page under Activities tab");

        calendarEventsPage.waitUntilLoaderScreenDisappear();
        String actualText = calendarEventsPage.viewPerPage.getText();
        String expectedText = "25";

        Assert.assertEquals(actualText, expectedText, "Verify that View Per Page is as expected");
        extentLogger.info("Verify that the View Per Page is '25' as expected");

        extentLogger.pass("viewPerPageNumberTest is passed");

    }


    @Test(dataProvider = "storeManagerData")
    public void numbersOfRecordsTest(String username, String password) {
        extentLogger = report.createTest("VyTrack Homework Tests- numbersOfRecords verification");
        logInPage = new LogInPage();
        logInPage.login(username, password);
        extentLogger.info("Log in to app as a store manager ");
        extentLogger.info("Test datas: username: " + username + " password: " + password);

        calendarEventsPage = new CalendarEventsPage();


        calendarEventsPage.navigateToModule(ConfigurationReader.get("tabName"), ConfigurationReader.get("moduleName"));
        extentLogger.info("Navigate to Calendar Events page under Activities tab");

        calendarEventsPage.waitUntilLoaderScreenDisappear();

        int totalRowNumber = calendarEventsPage.getTotalRowNumber();
        int recordsNumber = calendarEventsPage.getRecordsNumber();

        extentLogger.info("Verify that number of calendar events is equal to number of records");
        Assert.assertEquals(totalRowNumber, recordsNumber, "Verify that total rows number are eqaual to records number ");

        extentLogger.pass("numbersOfRecords verification test is passed");
    }

    @Test(dataProvider = "storeManagerData")
    public void checkBoxTest(String username, String password) {
        extentLogger = report.createTest("VyTrack Homework Tests- checkBoxTest");


        logInPage = new LogInPage();
        logInPage.login(username, password);
        extentLogger.info("Log in to app as a store manager ");
        extentLogger.info("Test datas: username: " + username + " password: " + password);

        calendarEventsPage = new CalendarEventsPage();

        calendarEventsPage.navigateToModule(ConfigurationReader.get("tabName"), ConfigurationReader.get("moduleName"));
        extentLogger.info("Navigate to Calendar Events page under Activities tab");
        extentLogger.info("Navigate to Activites tab and Calender Events page");
        calendarEventsPage.waitUntilLoaderScreenDisappear();

        boolean verificationOfTitleCheckBox = calendarEventsPage.titleCheckBoxVerification();

        extentLogger.info("Click top title checkbox and verify that all calender events are selected");
        Assert.assertTrue(verificationOfTitleCheckBox, "Verify that all title chechboxes has been selected after main checkbox selected");

        extentLogger.pass("checkBoxTest is passed");


    }
}
