package com.asd.framework.LoginAuthorization.Proxy;

import com.asd.framework.LoginAuthorization.RealSubject.LoginAuthorizationExecutor;
import com.asd.framework.LoginAuthorization.Role;
import com.asd.framework.LoginAuthorization.SubjectInterface.LoginAuthorizationInterface;
import javafx.scene.control.Control;

import java.util.HashMap;
import java.util.List;

/**
 * Created by manozct on 6/15/2017.
 */
public class LoginAuthorizationProxy implements LoginAuthorizationInterface {
    private LoginAuthorizationInterface loginAuthorization;

    public LoginAuthorizationProxy(String username,String password){
        if(username.equals("doctor")&&password.equals("doctor")){
            loginAuthorization=new LoginAuthorizationExecutor();
            System.out.println("login successs");
        }
    }


    @Override
    public void authorizationCheck(HashMap<String, List<Control>> hmapControl) {
        Role r=new Role();
        for(String rol:r.getRoles()){
            if(rol.equals(hmapControl.keySet().toString())){
                loginAuthorization.authorizationCheck(hmapControl);
            }
        }

    }
    public void hideAllControls(List<Control>controls){
        for(Control c:controls){
            c.setVisible(false);
        }

    }


}
