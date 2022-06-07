package com.bse2.projects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.sql.*;

public class events extends dbConnect {
    private String eventn, eventdesc, eventd;
    ObservableList<ObservableList> data;
    TableView tableview;
    private int eventsnumber;
    private static final String INSERT_QUERY = "INSERT INTO event (Title, Date, Description) VALUES (?, ?, ?)";
    public void retriveevents(TableView tableview) {
        data = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * from event";
            ResultSet rs = connectdb.createStatement().executeQuery(SQL);
            //adding table columns
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                System.out.println(col.toString());
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                tableview.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }
            //data being added to observable list
            while (rs.next()) {
                //Iterating Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row added " + row);
                try{
                    data.add(row);
                    this.eventsnumber=data.size();
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Couldnt add data");
                }
            //Setting up the table view to display results
            tableview.setItems(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertrow(String ett,String edsc, Date ed){
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (PreparedStatement preparedStatement = connectdb.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, ett);
            preparedStatement.setDate(2, ed);
            preparedStatement.setString(3, edsc);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            e.printStackTrace();
        }
    }
    Integer eventsnum(){return eventsnumber;}
}
