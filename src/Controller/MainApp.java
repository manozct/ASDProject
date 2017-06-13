package Controller;

import com.asd.framework.DataValidation.Context.ValidationContext;
import com.asd.framework.DataValidation.ValidationConstraint;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.util.List;
import java.util.Map;


/**
 * Created by manozct on 6/13/2017.
 */
public class MainApp extends Application {
    private Text actionText;
    @FXML
    private TextField txtName;
    @FXML
    private ComboBox<String> cmbCateory;
    @FXML private javafx.scene.control.Label lblOutput;
    @FXML private TextField txtMobile;
    @FXML private TextField txtEmail;
    @FXML private TextField txtHireDate;





    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../View/TestLayout.fxml"));

        Scene scene = new Scene(root, 700, 575);
        /*ObservableList<String> lst= FXCollections.observableArrayList("Cardiology","Radiology","Surgery");
        cmbCateory.setItems(lst);*/

        primaryStage.setTitle("Welcome");

        primaryStage.setScene(scene);
        primaryStage.show();




    }

    @Override
    public void init() throws Exception {
        super.init();

       // myBtn.addActionListener(new Button);

    }
    @FXML
    public void ShowOutput(ActionEvent actionEvent){
        //System.out.println("hello");
        //String name= txtName.getText();
        //System.out.println("name:"+name);
       // lblOutput.setText(name);

        ValidationContext validationContext=new ValidationContext();
        validationContext.addValidationConstraint(txtName,ValidationConstraint.REQUIRED);
        validationContext.addValidationConstraint(txtMobile,ValidationConstraint.REQUIRED);
        validationContext.addValidationConstraint(txtEmail,ValidationConstraint.REQUIRED);
        validationContext.addValidationConstraint(cmbCateory,ValidationConstraint.REQUIRED);
        validationContext.addValidationConstraint(txtHireDate,ValidationConstraint.REQUIRED);
        //validationContext.addValidationConstraint(txtName,ValidationConstraint.NUMBER);


        Map<Boolean, List<String>> map=validationContext.checkValidate();
        System.out.println(map);
        for(Map.Entry<Boolean, List<String>> entry : map.entrySet()){
            //String s=entry.getValue().get(0);
            for(String s:entry.getValue()){
                System.out.println(s);

            }

        }


    }


}
