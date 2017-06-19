package Controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    @FXML MenuItem myDashBoard;

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

    @FXML
    void viewDashBoard(ActionEvent event)  {
        System.out.println("test");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Appointment.fxml"));
        try {
            Stage stage=new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void designMainMenu(){
        JPopupMenu popupMenu = new JPopupMenu();

        JMenu deviceMenu = new JMenu("Add Device");

        popupMenu.add(deviceMenu);
        popupMenu.add(new JMenuItem("Delete Device"));
        popupMenu.add(new JMenuItem("Fire"));
        popupMenu.add(new JMenuItem("Fault"));
        popupMenu.add(new JMenuItem("Supress"));
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
