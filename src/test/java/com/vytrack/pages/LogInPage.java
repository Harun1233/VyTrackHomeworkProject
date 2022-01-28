package com.vytrack.pages;




import com.vytrack.utilites.ConfigurationReader;
import com.vytrack.utilites.Driver;
import com.vytrack.utilites.ExcelUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

public class LogInPage {

    public LogInPage(){
        PageFactory.initElements(Driver.get(),this);
    }

    //driver.findElement(By.id("prependedInput"));
    @FindAll({
            @FindBy(id = "prependedInput"),
            @FindBy(name ="_username")
    })
    public WebElement usernameInput;

    @FindBy(id = "prependedInput2")
    public WebElement passwordInput;

    //driver.findElement(By.id("_submit"));
    @FindBy(id = "_submit")
    public WebElement loginBtn;

    @DataProvider
    public Object[][] storeManagerData(){
        String worksheet=ConfigurationReader.get("excelWorkSheet");
        ExcelUtil strMngr=new ExcelUtil("src/test/resources/Vytracktestdata.xlsx",worksheet);

        String[][] dataArray = strMngr.getDataArray();

        return dataArray;
    }


    public void login(String username,String password){
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }

    /**
    public void loginAsStoreManager(){

        String username = ConfigurationReader.get("storemanager_username");
        String password = ConfigurationReader.get("storemanager_password");

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }

    public void loginAsDriver(){

        String username = ConfigurationReader.get("driver_username");
        String password = ConfigurationReader.get("driver_password");

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }**/

}
