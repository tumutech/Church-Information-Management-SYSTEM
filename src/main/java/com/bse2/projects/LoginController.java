package com.bse2.projects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class LoginController extends dbConnect implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ComboBox acctype;
    @FXML
    private Button login;
    @FXML
    TextField username,fname,lname,residence,hometown,mobileno,email;
    @FXML
    PasswordField password,npassword;
    @FXML
    DatePicker dob;
    @FXML
    Label message;
    @FXML
    ChoiceBox ministry;
    @FXML
    ComboBox gender;
    // a map to store credentials retrived from database.
    HashMap<String,String> account =new HashMap<String,String>();
    String select = "SELECT username,password FROM admin";
    String selectmem = "SELECT mobile,password FROM admin";
    members regmem = new members();
    private static final String INSERT_QUERY = "INSERT INTO members (fname, lname,Gender,Birthday,Residence,pob,ministry,mobile,email,password ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public void login(ActionEvent event) throws IOException {
        System.out.println("Logging in!");
        if (acctype.getSelectionModel().getSelectedItem() == "Admin"){
            adminlogin(event);
        }
        else if (acctype.getSelectionModel().getSelectedItem() == "Member"){
            memberlogin();
        }
        else{
            System.out.println("Please choose account type");
        }
    }
    public void adminlogin(ActionEvent event) throws IOException {
        System.out.println("Admin");
        try {
            PreparedStatement preparedStatement = connectdb.prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String ausername = resultSet.getString("username");
                String apassword = resultSet.getString("Password");
                account.put(ausername, apassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(account);
        String inputuser = username.getText();
        String inputpass = password.getText();
        //checking if user sign in details are in the map
        for (Map.Entry accnts : account.entrySet()) {
            System.out.println(inputuser);
            System.out.println(accnts.getKey() + " " + accnts.getValue());
            if (inputuser.equals(accnts.getKey()) && inputpass.equals(accnts.getValue())) {
                message.setText("Success signed in!");
                //Parent root =  FXMLLoader.load(Main.class.getResource("AdminHome.fxml"));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHome.fxml"));
                root = loader.load();
                AdminController adminctrl = loader.getController();
                adminctrl.setdetails(inputuser);
                System.out.println(username.getText());
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else if (inputuser.equals(accnts.getKey())) {
                message.setText("Wrong Password");
                password.clear();
            } else {
                message.setText("Invalid credentials");
            }

        }
    }
    public void memberlogin() throws IOException {
//        System.out.println("Member");
//        try {
//            PreparedStatement preparedStatement = connectdb.prepareStatement(selectmem);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                String ausername = resultSet.getString("mobile");
//                String apassword = resultSet.getString("Password");
//                account.put(ausername, apassword);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println(account);
//        String inputuser = username.getText();
//        String inputpass = password.getText();
//        //checking if user sign in details are in the map
//        for (Map.Entry accnts : account.entrySet()) {
//            System.out.println(inputuser);
//            System.out.println(accnts.getKey() + " " + accnts.getValue());
//            if (inputuser.equals(accnts.getKey()) && inputpass.equals(accnts.getValue())) {
//                message.setText("Success signed in!");
//                //Parent root =  FXMLLoader.load(Main.class.getResource("AdminHome.fxml"));
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHome.fxml"));
//                root = loader.load();
//                AdminController adminctrl = loader.getController();
//                adminctrl.setdetails(inputuser);
//                System.out.println(username.getText());
//                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                Scene scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
//            } else if (inputuser.equals(accnts.getKey())) {
//                message.setText("Wrong Password");
//                password.clear();
//            } else {
//                message.setText("Invalid credentials");
        // }

        //}
    }
    public void registernewmember(ActionEvent event){
        System.out.println(fname.getText());
        String nfname = fname.getText();
        String nlname = lname.getText();
        String ngender = gender.getSelectionModel().getSelectedItem().toString();
        Date ndob = Date.valueOf(dob.getValue());
        String nministry = ministry.getSelectionModel().getSelectedItem().toString();
        String nmobileno = mobileno.getText();
        String nnpassword = npassword.getText();
        String nresidence = residence.getText();
        String nemail = email.getText();
        String pob = hometown.getText();
        regmem.newmember(nfname,nlname,ngender,ndob,nresidence,pob,nministry,nmobileno,nemail,nnpassword);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        members regmem = new members();
        regmem.setgendernministry(gender,ministry);
        acctype.getItems().add("Admin");
        acctype.getItems().add("Member");
    }
}
