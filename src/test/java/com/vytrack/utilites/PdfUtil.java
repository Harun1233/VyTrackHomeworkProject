package com.vytrack.utilites;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PdfUtil {


    public String getPdfText() throws IOException {

        FileInputStream obj=new FileInputStream("src/test/resources/"+ConfigurationReader.get("pdfFileName"));
        PDDocument objDoc=PDDocument.load(obj);
        PDFTextStripper objPDFStripper=new PDFTextStripper();
        String text = objPDFStripper.getText(objDoc);


    return text;
    }
}
