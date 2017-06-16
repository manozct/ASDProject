package com.asd.framework.DataValidation.ConcreteStrategies;

import com.asd.framework.DataValidation.Strategy.ValidatorStrategy;

/**
 * Created by manozct on 6/13/2017.
 */
public class EmailValidation implements ValidatorStrategy {
    @Override
    public Boolean validate(String value) {
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if (EMAIL_REGEX.matches(value)) {
            return false;
        }
        return true;
    }
}
