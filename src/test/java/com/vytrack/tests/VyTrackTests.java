package com.vytrack.tests;

import com.vytrack.pages.LogInPage;
import com.vytrack.utilites.ExcelUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VyTrackTests extends TestBase {



    @Test
    public void test1(){
        extentLogger=report.createTest("VyTrack Homework Tests");

        LogInPage logInPage=new LogInPage();

        logInPage.loginAsStoreManager();






    }


}
