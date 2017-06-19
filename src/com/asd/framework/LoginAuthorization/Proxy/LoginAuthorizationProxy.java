package com.asd.framework.LoginAuthorization.Proxy;

import com.asd.framework.DatabaseConnection.Db.DbAccess;
import com.asd.framework.LoginAuthorization.RealSubject.LoginAuthorizationExecutor;
import com.asd.framework.LoginAuthorization.Role;
import com.asd.framework.LoginAuthorization.SubjectInterface.LoginAuthorizationInterface;
import javafx.scene.control.Control;
import javafx.scene.control.Menu;

import java.util.HashMap;
import java.util.List;

/**
 * Created by manozct on 6/15/2017.
 */
public class LoginAuthorizationProxy implements LoginAuthorizationInterface {
    private LoginAuthorizationInterface loginAuthorization;

<<<<<<< HEAD
    /*public LoginAuthorizationProxy(){
=======
   /* public LoginAuthorizationProxy(String username,String password){

>>>>>>> efe3665bbea0ac6e5956eb0f30855e00311d4a47
        if(username.equals("doctor")&&password.equals("doctor")){
            loginAuthorization=new LoginAuthorizationExecutor();
            System.out.println("login successs");
        }
    }*/


    @Override
    public void authorizationCheck(HashMap<String, List<Menu>> hmapControl) {
        Role r=new Role();
        for(String rol:r.getRoles()){
            if(rol.equals(hmapControl.keySet().toString())){
                loginAuthorization.authorizationCheck(hmapControl);
            }
        }

    }
    public void hideAllControls(List<Menu>controls){
        for(Menu c:controls){
            c.setVisible(false);
        }

    }


}
