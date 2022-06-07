package com.bse2.projects;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author TUMUTECH
 */

public class AdminController extends members implements Initializable {
    @FXML
    Label myview,mno,visitorno,eventlabel,financeno;
    @FXML
    private ListView<String> mylist;
    @FXML
    private Button conbutton,viewmember,addEventbutton,viewVisitors,savevisitors,vieweventbutton,financesave,financebutton;
    @FXML
    private TabPane tabpane;
    @FXML
    private Tab viewtab,addtab,eventtab;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private ChoiceBox<String> ministries;
    @FXML
    private TextField etitle,edesc,famount,ftrcode,fmobile,mfn,mln,mr,mh,mmno,memail,vln,vfn,vr,ve,vmobile;
    @FXML
    private PasswordField mpass;
    @FXML
    private DatePicker edate,dob,vd;
    @FXML
    private TableView tableview,visitortview,financetable,membertx;
    @FXML
    DialogPane dia;
    String[] items;
    Stage stage;
    Parent root;
//    private ObservableList<ObservableList> data;
    private  ObservableList<ObservableList> fdata;
    TabPane yiya;
    int memberslen;
    visitors ovisitors = new visitors();
    members omembers = new members();
    events oevents = new events();
    Finance finance = new Finance();
    //setting admin user
    public void setdetails(String adminu){
        myview.setText(adminu);
    }
    public void getmembers() {
        membertx.getColumns().clear();
        omembers.rmembers(membertx);
        mno.setText("Members("+omembers.retmemno().toString()+")");
    }
    public void addmember(){
        String mfname = mfn.getText();
        String lname = mln.getText();
        String genderc = gender.getSelectionModel().getSelectedItem();
        Date birthday = Date.valueOf(dob.getValue());
        String residence = mr.getText();
        String hometow = mh.getText();
        String ministry = ministries.getSelectionModel().getSelectedItem();
        String mobile = mmno.getText();
        String memai = memail.getText();
        String password = mpass.getText();
        omembers.newmember(mfname,lname,genderc,birthday,residence,hometow,ministry,mobile,memai,password);
        getmembers();
    }
    //a method to display financial information;
    public void viewfinance(){
        financetable.getColumns().clear();
        finance.retrivefinance(financetable);
        System.out.println("Number of Finance:"+finance.offeringnum());
        financeno.setText("Finance("+finance.offeringnum()+")");
    }
    public void addactiontab(){
        tabpane.getSelectionModel().select(addtab);
    }
    public void viewevent(){
        tableview.getColumns().clear();
        oevents.retriveevents(tableview);
        eventlabel.setText("Events("+oevents.eventsnum()+")");
        tableview.setPlaceholder(new Label("No events to display"));
    }
    //a function to push events to the database
    public void addevent(){
        String eventtitle = etitle.getText();
        String edescripion = edesc.getText();
        Date eventdate = Date.valueOf(edate.getValue());
        oevents.insertrow(eventtitle,edescripion,eventdate);
        viewevent();
    }

    public void getvisitors() {
        visitortview.getColumns().clear();
        ovisitors.retrievevisitors(visitortview);
        this.visitorno.setText("Visitors("+Integer.toString(ovisitors.updatenovisitor())+")");
    }
    public void getaddvisitorsbutton(){
        String vfname = vfn.getText();
        String vlname = vln.getText();
        String vresidence = vr.getText();
        String vevent = ve.getText();
        Date vdate = Date.valueOf(vd.getValue());
        String vmnumber = vmobile.getText();
        ovisitors.insertrow(vfname,vlname,vresidence,vevent,vdate,vmnumber);
        getvisitors();

    }
    public void printmem(ActionEvent e) throws Exception {
        Reports printgen = new Reports();
        printgen.genreport();
    }

    public void savefinance(ActionEvent event){
        financesave.setText("Save");
        String famnt = famount.getText();
        String ftrcod = ftrcode.getText();
        String na = fmobile.getText();
        finance.offering(famnt,ftrcod,na);
        viewfinance();
    }
    public void validateno(KeyEvent event){
        String mobile = event.getCharacter();
        if (mobile=="000000000"){
            //what to do in normal conditions
        }
        else{
            event.consume();
        }
    }
    public void logout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Logout");
        alert.setHeaderText("Are you sure you want to logout");
        alert.setContentText("This will erase all unsaved changes");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TO INTIALISE THINGS FROM HERE
        getmembers();
        getvisitors();
        viewevent();
        viewfinance();
        setgendernministry(gender,ministries);
        System.out.println("Welcome to the Church Information Management System");

    }
}
