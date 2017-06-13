package com.asd.framework.DataValidation.ConcreteStrategies;

import com.asd.framework.DataValidation.Strategy.ValidatorStrategy;

/**
 * Created by manozct on 6/13/2017.
 */
public class NumberValidator implements ValidatorStrategy {
    @Override
    public Boolean validate(String value) {
        if(!value.matches("[0-9]+")){
            return false;
        }
        return true;
    }
}
