package com.asd.framework.LoginAuthorization.RealSubject;

import com.asd.framework.LoginAuthorization.SubjectInterface.LoginAuthorizationInterface;


import javafx.scene.control.Control;
import javafx.scene.control.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by manozct on 6/15/2017.
 */
public class LoginAuthorizationExecutor implements LoginAuthorizationInterface {
    @Override
    public void authorizationCheck(HashMap<String, List<Menu>> hmapControl) {
        List<Control> lstControl=new ArrayList<>();
        for(Map.Entry<String,List<Menu>> hmp:hmapControl.entrySet()){
            for(Menu c:hmp.getValue()){
                c.setVisible(true);
            }
        }

    }




}
