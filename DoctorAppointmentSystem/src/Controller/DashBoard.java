package Controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by manozct on 6/15/2017.
 */
public class DashBoard extends Application {
    @FXML Menu viewId;
    @FXML Menu doctorAddId;
    @FXML Menu appointmentId;
    @FXML Menu customerListId;
    @FXML Menu doctorProfileId;
    @FXML MenuBar MainMenu;

    private HashMap<String,List<Menu>>hMapControlByRole=new HashMap<>();


    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainDashboard.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Doctor Appointment System");
        primaryStage.setScene(scene);

        //designMainMenu();

        primaryStage.show();


    }





    public void authorizationOperation(){
        List<Menu> lstControlId= Arrays.asList(viewId,doctorAddId,appointmentId,customerListId,doctorProfileId);
    }
    public HashMap<String,List<Menu>> showControlDoctor(){

       List<Menu> lstShowControl=Arrays.asList(appointmentId,customerListId,doctorProfileId);
       hMapControlByRole.put("Doctor",lstShowControl);
       return hMapControlByRole;

    }

    @FXML
    public void initialize() {
        System.out.println("initialize");
        viewId.setVisible(false);
    }



}
