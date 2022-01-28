package com.vytrack.tests;

import com.vytrack.pages.CalendarEventsPage;
import com.vytrack.pages.LogInPage;
import com.vytrack.utilites.ConfigurationReader;
import com.vytrack.utilites.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VyTrackTests extends TestBase {

    LogInPage logInPage;
    CalendarEventsPage calendarEventsPage;

    @Test
    public void subtitleTest(){
        extentLogger=report.createTest("VyTrack Homework Tests- Subtitle Verification");


       logInPage=new LogInPage();

        logInPage.loginAsStoreManager();
        extentLogger.info("Log in to app as a store manager ");
        extentLogger.info("Test datas: username: "+ ConfigurationReader.get("storemanager_username")+" password: "+ ConfigurationReader.get("storemanager_password"));

         calendarEventsPage=new CalendarEventsPage();


        calendarEventsPage.navigateToModule("Activities","Calendar Events");
        extentLogger.info("Navigate to Calendar Events page under Activities tab");

        extentLogger.info("Verify that the 'Options' has displayed at page subtitle");
        Assert.assertTrue(calendarEventsPage.optionsButton.isDisplayed(),"Verify that 'Options' button has been displayed on page subtitle");

        extentLogger.pass("subtitleTest has been passed");



    }

    @Test
    public void pageNumberTest(){
        extentLogger=report.createTest("VyTrack Homework Tests- PageNumber Verification");


        logInPage=new LogInPage();

        logInPage.loginAsStoreManager();
        extentLogger.info("Log in to app as a store manager ");
        extentLogger.info("Test datas: username: "+ ConfigurationReader.get("storemanager_username")+" password: "+ ConfigurationReader.get("storemanager_password"));

        calendarEventsPage=new CalendarEventsPage();


        calendarEventsPage.navigateToModule("Activities","Calendar Events");
        extentLogger.info("Navigate to Calendar Events page under Activities tab");


        String actualValue = calendarEventsPage.pageNumber.getAttribute("value");
        String expectedValue="1";

        Assert.assertEquals(actualValue,expectedValue,"Verify that the page number on Calendar Events page is 1 as default");
        extentLogger.info("Verify that the default page number at the page is 1");

        extentLogger.pass("pageNumberTest is passed");


    }

    @Test
    public void viewPerPageNumberTest(){


    }





}
