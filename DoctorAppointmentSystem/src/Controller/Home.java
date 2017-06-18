package Controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by manozct on 6/18/2017.
 */
public class Home extends Application {
    private Stage stage;
    private String role="";

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Home.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Doctor Appointment System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @FXML
    public void btnAdminLoginAction(ActionEvent actionEvent){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/Login.fxml"));
        try {
            stage=new Stage();
            stage.setTitle("Login Admin");
            role="Admin";
            //setRole("Admin");
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void btnPatientLoginAction(ActionEvent actionEvent){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/Login.fxml"));
        try {
            stage=new Stage();
            stage.setTitle("Login Patient");
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @FXML
    public void btnDoctorLoginAction(ActionEvent actionEvent){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/Login.fxml"));
        try {
            stage=new Stage();
            stage.setTitle("Login Doctor");
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
