package com.bse2.projects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Tableviewupdate extends dbConnect{
    ObservableList<ObservableList> data;
    public ArrayList<String> tablecol = new ArrayList<String>();
    int queryno;
    public TableView columns(TableView tableview,String myquery) {
        data = FXCollections.observableArrayList();
        try {
            String SQL = myquery;
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
                    this.queryno=data.size();
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Couldnt add data");
                }
                //Setting up the table view to display results
                tableview.setItems(data);
                //searching
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("daraaaa"+data);
        return tableview;
    }
    public ObservableList<ObservableList> myt(TableView x){
        this.data=x.getItems();
        System.out.println("i got this"+data);
        return data;
    }
    }