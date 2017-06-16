package com.asd.framework.DataValidation.ConcreteStrategies;

import com.asd.framework.DataValidation.Strategy.ValidatorStrategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by manozct on 6/12/2017.
 */
public class AlphaNumericValidator implements ValidatorStrategy {
    public AlphaNumericValidator() {
    }


    @Override
    public Boolean validate(String value) {
        String pattern= "^[a-zA-Z0-9]*$";
        Pattern r=Pattern.compile(pattern);
        Matcher matcher=r.matcher(value);
        if(matcher.matches()){
            return true;
        }
       return false;
    }
}
