package com.bse2.projects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Maincontroller implements Initializable {
    @FXML
    private  ComboBox<String> comboselect;
    String[] roles = {"Admin","Member"};
    String role;
    @FXML
    private Button continuen;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboselect.getItems().addAll(roles);
    }
    public void chooserole(ActionEvent event){
        role = this.comboselect.getSelectionModel().getSelectedItem();

    }
    public void gonext(ActionEvent event){
        if (role=="Member"){
            System.out.println("Member");
        }
        else if (role=="Admin"){
            System.out.println("Admin");
        }
        else{
            System.out.println("No choice made");
        }
    }
}
