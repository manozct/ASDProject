package com.asd.framework.DataValidation.Context;

import com.asd.framework.DataValidation.ConcreteStrategies.*;
import com.asd.framework.DataValidation.InputRetriever;
import com.asd.framework.DataValidation.Strategy.ValidatorStrategy;
import com.asd.framework.DataValidation.ValidationConstraint;
import javafx.scene.control.Control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by manozct on 6/13/2017.
 */
public class ValidationContext {
    private ValidatorStrategy validatorStrategy;
    public Boolean isValidate;
    private HashMap<Control, ValidationConstraint> validationHMap = new HashMap<>();

    public ValidatorStrategy getValidator(ValidationConstraint value) {
        ValidatorStrategy validatorStrategy=null;
        switch (value){
            case REQUIRED:
                validatorStrategy=new RequiredValidator();
                break;
            case ALPHANUMERIC:
                validatorStrategy=new AlphaNumericValidator();
                break;
            case NUMBER:
                validatorStrategy=new NumberValidator();
                break;
            case DATE:
                validatorStrategy=new DateValidator();
                break;
            case TIME:
                validatorStrategy=new TimeValidator();
                break;
            case DATETIME:
                validatorStrategy=new DateTimeValidator();
                break;
            case EMAIL:
                validatorStrategy=new EmailValidation();
                break;

        }
        return validatorStrategy;
    }

    public Map<Boolean, List<String>> checkValidate() {
        Map<Boolean, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        Boolean isValid = true;
        for (Map.Entry<Control, ValidationConstraint> entry : validationHMap.entrySet()) {
            Control control = entry.getKey();
            ValidationConstraint validationConstraint = entry.getValue();
            String value=InputRetriever.retrieveText(control);
            isValid = getValidator(validationConstraint).validate(value);
            if (!isValid){
                list.add(String.format("%s is not valid !!! %s", value, validationConstraint));
            }
        }
        map.put(isValid, list);
        System.out.println("map:");
        return map;
    }

    public void addValidationConstraint(Control control, ValidationConstraint constraint){
        validationHMap.put(control,constraint);

    }
}



