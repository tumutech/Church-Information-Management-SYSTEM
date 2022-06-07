package com.bse2.projects;

import java.sql.Date;

public class tumutech {
 //   public void getvisitors() throws SQLException {
//        ovisitors.retrievevisitors();
//        this.visitorno.setText("Visitors("+Integer.toString(ovisitors.updatenovisitor())+")");
//        visitortview.getColumns().clear();
//        String select = "SELECT id,fname,Gender FROM visitor";
//        //Extracting data from Database
//        ResultSet resultSet=null;
//        try {
//            PreparedStatement preparedStatement = connectdb.prepareStatement(select);
//            resultSet = preparedStatement.executeQuery();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        ObservableList dbData = FXCollections.observableArrayList(dataBaseArrayList(resultSet));
//
//        //Giving readable names to columns
//        for(int i=0 ; i<resultSet.getMetaData().getColumnCount(); i++) {
//            TableColumn column = new TableColumn<>();
//            switch (resultSet.getMetaData().getColumnName(i+1)) {
//                case "id":
//                    column.setText("ID #");
//                    break;
//                case "fname":
//                    column.setText("Person Name");
//                    break;
//                case "Gender":
//                    column.setText("Marital Status");
//                    break;
//                default: column.setText(resultSet.getMetaData().getColumnName(i+1)); //if column name in SQL Database is not found, then TableView column receive SQL Database current column name (not readable)
//                    break;
//            }
//            column.setCellValueFactory(new PropertyValueFactory<>(resultSet.getMetaData().getColumnName(i+1))); //Setting cell property value to correct variable from Person class.
//            visitortview.getColumns().add(column);
//        }
//
//        //Filling up tableView with data
//        visitortview.setItems(dbData);
//    }
//
//
//    public class Person {
//
//        IntegerProperty id = new SimpleIntegerProperty(); //variable names should be exactly as column names in SQL Database Table. In case if you want to use <int> type instead of <IntegerProperty>, then you need to use getter/setter procedures instead of xxxProperty() below
//        StringProperty fname = new SimpleStringProperty();
//        StringProperty Gender = new SimpleStringProperty();
//
//        public IntegerProperty idProperty() { //name should be exactly like this [IntegerProperty variable name (id) + (Property) = idProperty] (case sensitive)
//            return id;
//        }
//
//        public StringProperty fnameProperty() {
//            return fname;
//        }
//
//        public StringProperty GenderProperty() {
//            return Gender;
//        }
//
//        public Person(int idValue, String fnameValue, String GenderValue) {
//            id.set(idValue);
//            fname.set(fnameValue);
//            Gender.set(GenderValue);
//        }
//
//        Person(){}
//    }
//
//    //extracting data from ResulSet to ArrayList
//    private ArrayList dataBaseArrayList(ResultSet resultSet) throws SQLException {
//        ArrayList<Person> data =  new ArrayList<>();
//        while (resultSet.next()) {
//            Person person = new Person();
//            person.id.set(resultSet.getInt("id"));
//            person.fname.set(resultSet.getString("fname"));
//            person.Gender.set(resultSet.getString("Gender"));
//            data.add(person);
//        }
//        return data;
//    }
    // adding visitors
//            preparedStatement.setString(1, fname);
//            preparedStatement.setString(2, lname);
//            preparedStatement.setString(3, residence);
//            preparedStatement.setString(4, vevent);
//            preparedStatement.setDate(5, vdate);
//            preparedStatement.setString(6, vmobile);
//            System.out.println(preparedStatement);
//    ..... private static final String INSERT_QUERY = "INSERT INTO visitor (fname, lname,residence,vevent,vdate,vmobile) VALUES (?, ?, ?, ?, ?, ?)";
//    String fname, String lname, String, String residence, String vevent, Date vdate,String vmobile
}
