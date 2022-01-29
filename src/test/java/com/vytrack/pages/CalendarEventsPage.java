package com.vytrack.pages;




import com.vytrack.utilites.Driver;
import com.vytrack.utilites.PdfUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
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

    @FindBy(xpath = "//button[@class='btn btn-default btn-small dropdown-toggle']//input[@type='checkbox']")
    public WebElement titleCheckBox;

    @FindBy(xpath = "//tbody//tr//td[2]")
    public WebElement testersMeetings;

    @FindBy(xpath = "(//div[@class='section-content'])[1]")
    public WebElement meetingDetails;

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

    public boolean titleCheckBoxVerification() {
        int dummy=0;

        titleCheckBox.click();
        waitUntilLoaderScreenDisappear();

        List<WebElement> elements = Driver.get().findElements(By.xpath("//input[@tabindex='-1']"));
        for (int i = 0; i < elements.size(); i++) {
             if (elements.get(i).isSelected()){
                 dummy+=1;
             }

        }


        if(dummy==elements.size()){
            return true;
        }else{
            return false;
        }



    }

    public boolean comparisonPDFTexts() throws IOException {

        PdfUtil pdfUtil=new PdfUtil();
        String pdfText = pdfUtil.getPdfText();


        testersMeetings.click();
        String meetingDetailsText = meetingDetails.getText();


        if (pdfText.contains(meetingDetailsText)){
            return true;
        }else{
            return false;
        }




    }
}








