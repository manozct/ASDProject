package Controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by eessc on 6/17/2017.
 */
public class Appointment extends Application {

    @FXML
    private ComboBox<?> cmbCategories;

    @FXML
    private TextField txtPatientID;

    @FXML
    private TextField txtContact;

    @FXML
    private ComboBox<?> cmbDoctors;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtDOB;

    @FXML
    private TableColumn<?, ?> clmDate;

    @FXML
    private TableView<?> tblMedHistory;

    @FXML
    private Button btnBook;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> clmPrecription;

    @FXML
    private TableColumn<?, ?> clmTime;

    @FXML
    private TableColumn<?, ?> clmReason;

    @FXML
    private TextField txtID;

    @FXML
    private TableColumn<?, ?> clmDoctor;

    @FXML
    private TableColumn<?, ?> clmDiagnosis;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtMail;

    @FXML
    private TableColumn<?, ?> clmStatus;

    @FXML
    private Button btnSubmit;

    @FXML
    private TableView<?> tableTimeSlap;

    @FXML
    void submitAction(ActionEvent event) {

    }

    @FXML
    void book(ActionEvent event) {
        createTimeSlap();
    }


    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    void UpdatePatientProfile(ActionEvent event) {
        if(btnUpdate.textProperty().getValue().equals("Update")){
            txtName.editableProperty().setValue(true);
            txtAddress.editableProperty().setValue(true);
            txtContact.editableProperty().setValue(true);
            txtDOB.editableProperty().setValue(true);
            txtMail.editableProperty().setValue(true);
            txtAddress.editableProperty().setValue(true);
            btnUpdate.textProperty().setValue("Save");
        }
        else
        {
            txtName.editableProperty().setValue(false);
            txtAddress.editableProperty().setValue(false);
            txtContact.editableProperty().setValue(false);
            txtDOB.editableProperty().setValue(false);
            txtMail.editableProperty().setValue(false);
            txtAddress.editableProperty().setValue(false);
            btnUpdate.textProperty().setValue("Update");

        }


    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../View/Appointment.fxml"));
        Parent root=loader.load();
        Scene scene=new Scene(root,900,600);
        primaryStage.setScene(scene);
        primaryStage.show();


    }



    public void FillData(ResultSet rs, TableView tView) {
        ObservableList data = null;
        try {

            while (rs.next()) {
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                    System.out.println(row);
                }

                data.add(row);

            }
            tView.setItems(data);

        } catch (SQLException ex) {
            ex.getStackTrace();
        }

    }

    public void createTimeSlap(){
        //tableView.getColumns().add(column);
        ObservableList<String> row = FXCollections.observableArrayList();
        TableColumn clm=new TableColumn();
        clm.styleProperty().setValue("-fx-background-color");
        clm.setText("10:15");
        tableTimeSlap.getColumns().add(clm);
        row.add("-");


    }





}
