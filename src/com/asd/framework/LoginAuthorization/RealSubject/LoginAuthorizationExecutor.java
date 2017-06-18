package com.asd.framework.LoginAuthorization.RealSubject;

import com.asd.framework.LoginAuthorization.SubjectInterface.LoginAuthorizationInterface;


import javafx.scene.control.Control;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by manozct on 6/15/2017.
 */
public class LoginAuthorizationExecutor implements LoginAuthorizationInterface {
    @Override
    public void authorizationCheck(HashMap<String, List<Control>> hmapControl) {
        List<Control> lstControl=new ArrayList<>();
        for(Map.Entry<String,List<Control>> hmp:hmapControl.entrySet()){
            for(Control c:hmp.getValue()){
                c.setVisible(true);
            }
        }

    }




}
