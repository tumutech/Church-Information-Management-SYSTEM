package com.bse2.projects;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * @author TUMUTECH
 */

public class members extends  dbConnect{
    //declaring variables for member
    private int memno;
    Tableviewupdate tvu = new Tableviewupdate();
    private static final String INSERT_QUERY = "INSERT INTO members (fname, lname,Gender,Birthday,Residence,pob,ministry,mobile,email,password ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public void rmembers(TableView tableview){
        tvu.columns(tableview,"SELECT No, fname,lname,Gender,Residence,ministry,mobile FROM members");
        tvu.myt(tableview);

    }
    public void retrivemembers(){
        System.out.println("Nothing goes here");
    }
    Integer retmemno(){
        this.memno = tvu.queryno;
        return memno;
    }
    public void setgendernministry(ComboBox gender, ChoiceBox ministries){
        gender.getItems().add("Male");
        gender.getItems().add(("Female"));
        //setting up ministries
        ministries.getItems().add("Evangelism");
        ministries.getItems().add("Praise and Worship");
        ministries.getItems().add("Ashers");
        ministries.getItems().add("Youth");
        ministries.getItems().add("Media and ICT");
    }
    public void newmember(String fname, String lname, String gender, Date Birthday,String residence,String pob,String ministry, String Mobile, String email,String password){
        try (PreparedStatement preparedStatement = connectdb.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setString(3, gender);
            preparedStatement.setDate(4, Birthday);
            preparedStatement.setString(5, residence);
            preparedStatement.setString(6, pob);
            preparedStatement.setString(7, ministry);
            preparedStatement.setString(8, Mobile);
            preparedStatement.setString(9, email);
            preparedStatement.setString(10, password);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            e.printStackTrace();
        }
    }
}
