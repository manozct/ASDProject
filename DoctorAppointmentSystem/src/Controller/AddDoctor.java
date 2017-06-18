package Controller;

import com.asd.framework.DataValidation.Context.ValidationContext;
import com.asd.framework.DataValidation.ValidationConstraint;
import com.asd.framework.DatabaseConnection.Db.DatabaseType;
import com.asd.framework.DatabaseConnection.Db.DbAccess;
import com.asd.framework.DatabaseConnection.Db.DbConnection;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by manozct on 6/13/2017.
 */
public class AddDoctor extends Application {
    private Text actionText;
    @FXML
    private Label lblOutput;
    @FXML
    private TextField txtName;
    @FXML
    private ComboBox<String> cmbCategory = new ComboBox<>();
    @FXML
    private TextField txtMobile;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtHireDate;

    @FXML private RadioButton rdbMale;
    @FXML private RadioButton rdbFeamle;
    @FXML
    private Button closeBtn;
    @FXML
    ListView<String> lstViewErrors = new ListView<>();
    Scene scene2;
    Stage popup = new Stage();
    FlowPane pane2;
    //private Popup popup=new Popup();


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/TestLayout.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toString());
        primaryStage.setTitle("Welcome");
        pane2 = new FlowPane();
        pane2.setVgap(10);
        scene2 = new Scene(pane2, 200, 100);
        popup.setScene(scene2);

        primaryStage.setScene(scene);
        primaryStage.show();


    }

    @Override
    public void init() throws Exception {
        super.init();
        getConnection();


    }

    @FXML
    public void initialize() {
        cmbCategory.getItems().addAll("Cardiology", "Radiology", "Surgery ");

    }

    private Popup createPopup() {
        final Popup popup = new Popup();
        popup.setAutoHide(true);
        popup.setX(300);
        popup.setY(200);
        popup.getContent().addAll(new Circle(25, 25, 50, Color.AQUAMARINE));
        return popup;
    }


    @FXML
    public void submitBtnAction(ActionEvent actionEvent) {

        ValidationContext validationContext = new ValidationContext();
        validationContext.addValidationConstraint(txtName, Arrays.asList(ValidationConstraint.REQUIRED));
        validationContext.addValidationConstraint(txtMobile, Arrays.asList(ValidationConstraint.REQUIRED, ValidationConstraint.NUMBER));
        validationContext.addValidationConstraint(txtEmail, Arrays.asList(ValidationConstraint.REQUIRED, ValidationConstraint.EMAIL));
        validationContext.addValidationConstraint(cmbCategory, Arrays.asList(ValidationConstraint.REQUIRED));
        validationContext.addValidationConstraint(txtHireDate, Arrays.asList(ValidationConstraint.REQUIRED, ValidationConstraint.DATE));

        /*if any validation error exist then only popup window show*/
        validationContext.checkValidate();
       // System.out.println("isvalid:"+validationContext.isValid());

        if(!validationContext.isValid()){
            List<String> errors = validationContext.getErrors();
            ObservableList<String> obErrors = FXCollections.observableArrayList(errors);
            lstViewErrors.setItems(obErrors);

            Scene scene3 = new Scene(new VBox(lstViewErrors));
            popup.setScene(scene3);
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setHeight(320);
            popup.setWidth(430);
            popup.setTitle("Validation Errors");
            popup.showAndWait();

        }
        if(validationContext.isValid()){

            try {

                //ResultSet rs = DbAccess.table("doctor").get();
               // System.out.println(rs.getString(1).toString());
                //ResultSet rs = DbAccess.table("customer")
                //.select("id", "name").where("ID","1").orWhere("ID","10").get();

                /*ResultSet rs= DbAccess.table("customer")
                        .select("ID","name")
                        .get();

                while (rs.next()) {
                    System.out.println(rs.getString(1).toString());
                }*/


                Map<String,String> valuess=new HashMap<>();
                 String gender="";
                 if(rdbMale.isSelected()){
                     gender="Male";
                 }
                 else{
                     gender="Female";
                 }
                System.out.println(gender);

                valuess.put("Name", txtName.getText());
                valuess.put("Mobile", txtMobile.getText());
                valuess.put("Email", txtEmail.getText());
                valuess.put("Category", cmbCategory.getSelectionModel().getSelectedItem());
                valuess.put("Gender",gender);
                valuess.put("HireDate",txtHireDate.getText());

                DbAccess.table("doctor")
                        .values(valuess).insert();
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setContentText("Data saved sucessfully..");
                alert.showAndWait();



            } catch (Exception e) {
                e.printStackTrace();
            }
        }




    }

    @FXML
    private void closeButtonAction(ActionEvent actionEvent) {

        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();


    }
    public void getConnection(){
        try {
            DbConnection.getCOnnection();
            Connection conn = DbConnection.dbConnectionObj.connect(DatabaseType.MySql, "localhost",
                    3306, "appointmentsystem", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
