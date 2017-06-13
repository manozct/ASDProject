package com.asd.framework.DataValidation.ConcreteStrategies;

import com.asd.framework.DataValidation.Strategy.ValidatorStrategy;

/**
 * Created by manozct on 6/12/2017.
 */
public class RequiredValidator implements ValidatorStrategy {



    @Override
    public Boolean validate(String value) {
        if(value==null || value.isEmpty()){
            return false;

        }

        return true;

    }
}
