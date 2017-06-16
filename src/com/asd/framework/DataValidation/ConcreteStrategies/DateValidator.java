package com.asd.framework.DataValidation.ConcreteStrategies;

import com.asd.framework.DataValidation.Strategy.ValidatorStrategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by manozct on 6/13/2017.
 */
public class DateValidator implements ValidatorStrategy {
    @Override
    public Boolean validate(String value) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("MM-dd-yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(value);
            return true;
        } catch (ParseException e) {
            //e.printStackTrace();
            return false;
        }

       // return false;
    }
}
