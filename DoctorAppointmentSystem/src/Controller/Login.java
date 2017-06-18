package Controller;

import com.asd.framework.LoginAuthorization.Proxy.LoginAuthorizationProxy;
import com.asd.framework.LoginAuthorization.Role;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by manozct on 6/17/2017.
 */
public class Login extends Application {

    @FXML private TextField txtUserName;
    @FXML private TextField txtPassword;
    @FXML private Button btnLogin;
    private FXMLLoader loader;
    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        loader = new FXMLLoader(getClass().getResource("../View/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    @FXML
    public void initialize() {
        List<String> roles = Arrays.asList("Admin", "Doctor", "Nurse", "Patient");
        Role role = new Role(roles);
       // cmbRole.getItems().addAll(role.getRoles());

    }
    @FXML
    public void loginAction(ActionEvent actionEvent){
        String userName=txtUserName.getText().trim();
        String password=txtPassword.getText().trim();
        LoginAuthorizationProxy loginAuthorizationProxy=new LoginAuthorizationProxy(userName,password);
        //loginAuthorizationProxy.hideAllControls();
       // System.out.println("login");
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.close();


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/MainDashBoard.fxml"));
        try {

            stage=new Stage();
            Home home=new Home();
            System.out.println("role:"+home.getRole());
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
