package com.asd.framework.LoginAuthorization.Proxy;

import com.asd.framework.LoginAuthorization.Role;
import com.asd.framework.LoginAuthorization.SubjectInterface.LoginAuthorizationInterface;

/**
 * Created by manozct on 6/15/2017.
 */
public class LoginAuthorizationProxy implements LoginAuthorizationInterface {
    private LoginAuthorizationInterface loginAuthorization;

    @Override
    public void authorizationCheck(String role) {
        Role r = new Role();
        for (String rol : r.getRoles()) {
            if (rol.equals(role)) {
                loginAuthorization.authorizationCheck(role);

            }

        }

    }
}
