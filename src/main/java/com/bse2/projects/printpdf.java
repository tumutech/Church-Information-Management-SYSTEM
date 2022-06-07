package com.bse2.projects;
import com.itextpdf.text.Document;

import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.xdevapi.Table;

import java.io.FileOutputStream;
import java.util.Scanner;
import  com.itextpdf.text.Paragraph;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Font;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
// used to read a pdf document
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.layout.element.*;


public class printpdf {
    public void pdf(){
        Document document = new Document();
        Scanner filename = new Scanner(System.in);
        System.out.println("enter file name:");
        String infilename = filename.nextLine();
        try
        {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(infilename+".pdf"));
            document.open();
            Paragraph header = new Paragraph("Church Information");
            Paragraph churchinfo = new Paragraph("Church Name:");
            Paragraph address = new Paragraph("P.O.BOX");
            Paragraph city = new Paragraph("City:");
            //formarting
            churchinfo.setAlignment(Paragraph.ALIGN_CENTER);
            address.setAlignment(Paragraph.ALIGN_RIGHT);
            address.setAlignment(Paragraph.ALIGN_RIGHT);
            Chapter headersection = new Chapter(header,1);
            headersection.add(churchinfo);
            headersection.add(address);
            document.add(headersection);

            //Set attributes here
            document.addAuthor("Tumutech");
            document.addCreationDate();
            document.addCreator("admin");
            document.addTitle("Report ");
            document.addSubject("Pdf Generation");

            document.close();
            writer.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
