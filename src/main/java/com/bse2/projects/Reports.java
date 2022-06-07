package com.bse2.projects;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.Background;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.TabStop;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPTable;
import javafx.collections.ObservableList;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;

public class Reports extends dbConnect{
    //to snapshot
   // WritableImage image = ghgghgfgf.snapshot(new SnapshotParameters(),null);
//    try{
//        ImageIO.write(SwingFXUtils.fromFXImage(image,null),"png",file)
//    }catch(IOException e){
//
//    }
    public Reports() throws FileNotFoundException {
    }
    public void printlist(ObservableList<String> obsl){
        System.out.println(obsl);
    }
    public void genreport() throws Exception {
        statistics stat = new statistics();
        // Creating a PdfDocument object
        String dest = "general.pdf";
        
        PdfWriter writer = new PdfWriter(dest);

        // Creating a PdfDocument object
        PdfDocument pdf = new PdfDocument(writer);

        // Creating a Document object
        Document doc = new Document(pdf);
        Paragraph h = new Paragraph("Church Information Mangement System");
        // Creating a table
        float [] pointColumnWidths = {200F, 150F};
        Table table = new Table(pointColumnWidths);

        // Adding cells to the table
        table.addCell("Total Members");
        table.addCell(Integer.toString(stat.memno()));
        table.addCell("Total Vistors");
        table.addCell(Integer.toString(stat.visitno()));
        table.addCell("Total Financial Transactions");
        table.addCell("UGX "+ String.format("%,d",stat.financeno())+"/=");
        table.addCell("Total Events");
        table.addCell(Integer.toString(stat.eventsno()));
        //styling table.
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);

        // Adding Table to document
        doc.add(table);
        // Closing the document
        doc.close();
        System.out.println("Table created successfully..");
        stat.memno();
    }
    public static void main(String[] args) throws FileNotFoundException {//membersreport() throws FileNotFoundException {
        statistics stat = new statistics();
        int msize=stat.memno();
        System.out.println(msize);
        String dest = "members.pdf";
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf);
        // Creating a table
        float [] pointColumnWidths = {20F, 100F, 100F,20F,100F,100F,100F};
        Table table = new Table(pointColumnWidths);
        // Adding cells to the table
        for(int i=1;i<msize; i++){
            System.out.println("index:"+i);
//            table.addCell(Integer.toString(i));
//            table.addCell("First Name");
//            table.addCell("Last Name");
//            table.addCell("Gender");
//            table.addCell("Residence");
//            table.addCell("Ministry");
//            table.addCell("Mobile");
        }
        table.addCell("No.");
        table.addCell("First Name");
        table.addCell("Last Name");
        table.addCell("Gender");
        table.addCell("Residence");
        table.addCell("Ministry");
        table.addCell("Mobile");
        for(int i=1;i<=msize; i++){
            System.out.println("index:"+i);
            table.addCell(Integer.toString(i));
            table.addCell(stat.fname);
            table.addCell("Last Name");
            table.addCell("Gender");
            table.addCell("Residence");
            table.addCell("Ministry");
            table.addCell("Mobile");
        }

        //styling table.
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);

        // Adding Table to document
        doc.add(table);
        //pdf table

        // Closing the document
        doc.close();

        System.out.println("Member report  created successfully..");

    }
}
class statistics extends dbConnect {
    private static final String MEMBER_QUERY = "SELECT * FROM members";
    private static final String VISITOR_QUERY = "SELECT * FROM visitor";
    private static final String FINANCE_QUERY = "SELECT * FROM offering";
    private static final String EVENT_QUERY = "SELECT * FROM event";
    int memberno,visitorno,fino,eventsnot;
    String fname;
    ArrayList<String> membert = new ArrayList<>();
    public int memno(){
        try{
            ResultSet rs = connectdb.createStatement().executeQuery(MEMBER_QUERY);
            while (rs.next()){
                this.memberno = memberno + 1;
                this.fname = rs.getString("fname");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return memberno;
    }
    public int visitno(){
        try{
            ResultSet rs = connectdb.createStatement().executeQuery(VISITOR_QUERY);
            while (rs.next()){
                this.visitorno = visitorno + 1;

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return visitorno;
    }
    public int financeno(){
        try{
            ResultSet rs = connectdb.createStatement().executeQuery(FINANCE_QUERY);
            while (rs.next()){
                this.fino = fino + rs.getInt("Amount");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return fino;
    }
    public int eventsno(){
        try{
            ResultSet rs = connectdb.createStatement().executeQuery(EVENT_QUERY);
            while (rs.next()){
                this.eventsnot = eventsnot + 1;

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return eventsnot;
    }

}