package com.bse2.projects;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    //first declare a Connection name
    public Connection databaseLink;
    // create a method for setting up a connection
    public Connection getConnection(){
        // variables to store the connection credentials.
        String Databasename="cims1";
        String user="root";
        String password="";
        String url="jdbc:mysql://localhost/" + Databasename;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,user,password);
            System.out.println("Database Connected");

        } catch (Exception e){
            System.out.println(e);
        }
        return databaseLink;
    }
}
