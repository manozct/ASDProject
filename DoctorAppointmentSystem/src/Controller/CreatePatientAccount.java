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
public class CreatePatientAccount extends Application {
    @FXML
    private TextField txtPatientAddress;

    @FXML
    private TextField txtPatientUsername;

    @FXML
    private TextField txtPatientName;

    @FXML
    private Button btnCreatePatientAccount;

    @FXML
    private TextField txtPatientContact;

    @FXML
    private TextField txtPatientDob;

    @FXML
    private TextField txtPatientPassword;


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/CreatePatient.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Patient Registration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @FXML
    void createAccountAction(ActionEvent event) {

    }
}
