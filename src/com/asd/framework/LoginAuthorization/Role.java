package com.asd.framework.LoginAuthorization;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manozct on 6/15/2017.
 */
public class Role {

    private List<String> roles=new ArrayList<>();

    public Role() {
    }

    public Role(List<String> role) {

        addRoles(role);

    }
    private void addRoles(List<String> role){
        roles.addAll(role);

    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
