package com.asd.framework.LoginAuthorization.SubjectInterface;

import com.asd.framework.LoginAuthorization.Role;
import javafx.scene.control.Control;

import java.util.HashMap;
import java.util.List;

/**
 * Created by manozct on 6/15/2017.
 */
public interface LoginAuthorizationInterface {
    public void authorizationCheck(HashMap<String,List<Control>> hmapControl);
}
