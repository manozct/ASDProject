package com.asd.framework.DataValidation.ConcreteStrategies;

import com.asd.framework.DataValidation.Strategy.ValidatorStrategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by manozct on 6/13/2017.
 */
public class EmailValidation implements ValidatorStrategy {
    @Override
    public Boolean validate(String value) {
        String EMAIL_REGEX ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern r=Pattern.compile(EMAIL_REGEX);
        Matcher matcher=r.matcher(value);

        if (matcher.matches()) {

            return true;
        }

        return false;
    }
}
