package com.bse2.projects;
import javafx.scene.control.TableView;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author TUMUTECH
 */
public class Finance extends dbConnect{
    Tableviewupdate ftableupdate = new Tableviewupdate();
    private int offeringnumber;
    private static final String INSERT_QUERY = "INSERT INTO offering (Amount, Trcode, na) VALUES (?, ?, ?)";
    public void retrivefinance(TableView tableview) {
        ftableupdate.columns(tableview,"SELECT * FROM offering");
    }
    Integer offeringnum(){return ftableupdate.queryno;}
    public void offering(String Amount, String Trcode, String na){
        try (PreparedStatement preparedStatement = connectdb.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, Amount);
            preparedStatement.setString(2, Trcode);
            preparedStatement.setString(3, na);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
