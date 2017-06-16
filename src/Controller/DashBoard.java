package Controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * Created by manozct on 6/15/2017.
 */
public class DashBoard extends Application {
    @FXML
    Menu viewId=new Menu();
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainDashboard.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Doctor Appointment System");
        primaryStage.setScene(scene);
        System.out.println(viewId);
        viewId.setDisable(true);

        primaryStage.show();


    }
}
