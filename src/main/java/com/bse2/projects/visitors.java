package com.bse2.projects;

import javafx.scene.control.TableView;

import java.sql.*;

/**
 * @author TUMUTECH
 */

public class visitors extends dbConnect{
    String fname,lname,eventattended,residence,phoneno,dateofvisit;
    int num_visitors;
    Tableviewupdate vtupdate = new Tableviewupdate();
    private static final String INSERT_QUERY = "INSERT INTO visitor (fname, lname,Residence,event,Birthday,mobile) VALUES (?, ?, ?, ?, ?, ?)";

    /**
     * This will diplay all visitors in a table.
     * @param vtview
     */
    public void retrievevisitors(TableView vtview) {
        vtupdate.columns(vtview,"SELECT * FROM visitor");
    }

    /**
     * num_visitors is the number of visitors got from the number of rows in the visitor table
     * @return
     */
    public int updatenovisitor(){
        this.num_visitors = vtupdate.queryno;
        return num_visitors;
    }
    //pushing visitors to the database
    public void insertrow(String fname,String lname, String residence, String vevent, Date vdate, String vmobile){
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (PreparedStatement preparedStatement = connectdb.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setString(3, residence);
            preparedStatement.setString(4, vevent);
            preparedStatement.setDate(5, vdate);
            preparedStatement.setString(6, vmobile);
            System.out.println(preparedStatement);
            System.out.println("Added visitor to database");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            e.printStackTrace();
        }
    }
    }
