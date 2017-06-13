package com.asd.framework.DataValidation.ConcreteStrategies;

import com.asd.framework.DataValidation.Strategy.ValidatorStrategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by manozct on 6/13/2017.
 */
public class TimeValidator implements ValidatorStrategy {
    @Override
    public Boolean validate(String value) {
        String pattern = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";
        Pattern r=Pattern.compile(pattern);
        Matcher matcher=r.matcher(value);
        if(matcher.matches()){
            return false;
        }

        return true;
    }
}
