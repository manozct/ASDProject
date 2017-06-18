package Controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by manozct on 6/18/2017.
 */
public class CreateDoctor extends Application {
    @FXML
    private TextField txtDoctorContact;

    @FXML
    private TextField txtDoctorAddress;

    @FXML
    private TextField txtDoctorEmail;

    @FXML
    private TextField txtDoctorName;

    @FXML
    private TextField txtDoctorCategory;

    @FXML
    private Button btnCreateDoctor;

    @FXML
    private TextField txtDoctorUserName;

    @FXML
    private TextField txtDoctorPassword;

    @FXML
    private TextField txtDoctorDob;

    @FXML
    void createDoctor(ActionEvent event) {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/CreateDoctor.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 900, 700);
        primaryStage.setTitle("Doctor Registration");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
