package com.asd.framework.LoginAuthorization;

import java.util.Arrays;
import java.util.List;

/**
 * Created by manozct on 6/15/2017.
 */
public class Client {
    public static void main(String[] args) {
        List<String> roles = Arrays.asList("Admin", "Doctor", "Nurse", "Patient");
        Role role = new Role(roles);
        for (String s : role.getRoles()) {
            System.out.println(s);

        }
    }
}
