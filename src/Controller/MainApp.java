package Controller;

import com.asd.framework.DataValidation.Context.ValidationContext;
import com.asd.framework.DataValidation.ValidationConstraint;
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

import java.util.Arrays;
import java.util.List;


/**
 * Created by manozct on 6/13/2017.
 */
public class MainApp extends Application {
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
    @FXML
    private RadioButton groupGender;
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
    public void ShowOutput(ActionEvent actionEvent) {

        ValidationContext validationContext = new ValidationContext();
        validationContext.addValidationConstraint(txtName, Arrays.asList(ValidationConstraint.REQUIRED));
        validationContext.addValidationConstraint(txtMobile, Arrays.asList(ValidationConstraint.REQUIRED, ValidationConstraint.NUMBER));
        validationContext.addValidationConstraint(txtEmail, Arrays.asList(ValidationConstraint.REQUIRED, ValidationConstraint.EMAIL));
        validationContext.addValidationConstraint(cmbCategory, Arrays.asList(ValidationConstraint.REQUIRED));
        validationContext.addValidationConstraint(txtHireDate, Arrays.asList(ValidationConstraint.REQUIRED, ValidationConstraint.DATE));

        /*if any validation error exist then only popup window show*/
        validationContext.checkValidate();
        System.out.println("isvalid:"+validationContext.isValid());
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




    }

    @FXML
    private void closeButtonAction(ActionEvent actionEvent) {

        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();


    }


}
