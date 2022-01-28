package com.vytrack.pages;




import com.vytrack.utilites.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CalendarEventsPage extends BasePage {

    public CalendarEventsPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css = "[title='Create Calendar event']")
    public WebElement createCalendarEvent;

    @FindBy(xpath = "//div[@class='btn-group actions-group']")
    public WebElement optionsButton;

    @FindBy(css = ".icons-holder>li>input")
    public WebElement pageNumber;

    @FindBy(xpath = "//button[@class='btn dropdown-toggle ']")
    public WebElement viewPerPage;

    @FindBy(xpath = "//label[contains(text(),'Total')]")
    public WebElement recordsNumber;

    @FindBy(xpath = "//i[@class='fa-chevron-right hide-text']")
    public WebElement rightClickRowForNextTable;

    @FindBy(xpath = "//th[@class='select-all-header-cell renderable grid-cell grid-header-cell grid-header-cell-massAction']//div//button[@class='btn btn-default btn-small dropdown-toggle']")
    public WebElement titleCheckBox;

    public int getTotalRowNumber() {

        List<WebElement> pageRowsFirst = Driver.get().findElements(By.cssSelector(".grid-row"));
        int firstPageRowNumber = pageRowsFirst.size();
        rightClickRowForNextTable.click();
        List<WebElement> pageRowsSecond = Driver.get().findElements(By.cssSelector(".grid-row"));

        int secondPageRowNumber = pageRowsSecond.size();


        return firstPageRowNumber + secondPageRowNumber;
    }

    public int getRecordsNumber() {

        String rawTextOfRecordsNumber = recordsNumber.getText();

        String[] ofs = rawTextOfRecordsNumber.split("Of");

        String substring = ofs[1].trim().substring(0, 2);


        return Integer.parseInt(substring);

    }

    public boolean titleCheckBox() {
        int dummy=0;
        boolean flag = false;
        titleCheckBox.click();

        List<WebElement> elements = Driver.get().findElements(By.xpath("//td[@class='select-row-cell renderable grid-cell grid-body-cell grid-body-cell-massAction']"));
        for (WebElement element : elements) {
            if (element.isSelected()) {
                dummy+=1;
            }
        }

        if(dummy==elements.size()){
            flag=true;
        }

        return flag;
    }
}








