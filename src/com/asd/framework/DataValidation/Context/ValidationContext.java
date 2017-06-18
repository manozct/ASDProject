package com.asd.framework.DataValidation.Context;

import com.asd.framework.DataValidation.ConcreteStrategies.*;
import com.asd.framework.DataValidation.InputRetriever;
import com.asd.framework.DataValidation.Strategy.ValidatorStrategy;
import com.asd.framework.DataValidation.ValidationConstraint;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;

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
    private HashMap<Control, List<ValidationConstraint>> validationHMap = new HashMap<>();
    private Map<Boolean, List<String>> map = new HashMap<>();

    public ValidatorStrategy getValidator(ValidationConstraint value) {
        ValidatorStrategy validatorStrategy = null;
        switch (value) {
            case REQUIRED:
                validatorStrategy = new RequiredValidator();
                break;
            case ALPHANUMERIC:
                validatorStrategy = new AlphaNumericValidator();
                break;
            case NUMBER:
                validatorStrategy = new NumberValidator();
                break;
            case DATE:
                validatorStrategy = new DateValidator();
                break;
            case TIME:
                validatorStrategy = new TimeValidator();
                break;
            case DATETIME:
                validatorStrategy = new DateTimeValidator();
                break;
            case EMAIL:
                validatorStrategy = new EmailValidation();
                break;

        }
        return validatorStrategy;
    }

    public Map<Boolean, List<String>> checkValidate() {

        List<String> list = new ArrayList<>();
        Boolean isValid = true;
        for (Map.Entry<Control, List<ValidationConstraint>> entry : validationHMap.entrySet()) {
            Control control = entry.getKey();
            //System.out.println(InputRetriever.getControlId(control));

            List<ValidationConstraint> validationConstraint = entry.getValue();
            for (ValidationConstraint constraint : validationConstraint) {

                String value = InputRetriever.retrieveText(control);
               // System.out.println("CheckValidate method:"+isValid);


                if (!getValidator(constraint).validate(value)) {
                    if (control instanceof TextField)

                        ((TextField) control).getStyleClass().add("error");


                    String errMsg = InputRetriever.getControlId(control) + ":" + getErrorMsg(constraint);
                    //list.add(String.format("%s is not valid !!! %s", value, validationConstraint));
                    list.add(errMsg);

                    isValid =false;

                }
                // remove error class (red border)
                // ((TextField)control).getStyleClass().remove("error");
            }
        }
        map.put(isValid, list);

        return map;
    }

    public String getErrorMsg(ValidationConstraint validationConstraint) {
        String msg = "";
        switch (validationConstraint) {
            case REQUIRED:
                msg = "This Field must be Required !!!";
                break;
            case ALPHANUMERIC:
                msg = "This Field must be Alphanumeric !!!";
                break;
            case NUMBER:
                msg = "This Field must consist Number !!!";
                break;
            case DATE:
                msg = "This Field must be Date in MM-dd-yyyy format !!!";
                break;
            case TIME:
                msg = "This Field must be valid time in HH:MM format !!!";
                break;
            case DATETIME:
                msg = "This Field must consist DateTIme format !!!";
                break;
            case EMAIL:
                /*System.out.println("case Email");*/
                msg = "This Field must consist valid Email !!!";
                break;

        }
        return msg;

    }

    public void addValidationConstraint(Control control, List<ValidationConstraint> constraint) {
        validationHMap.put(control, constraint);


    }


    public List<String> getErrors() {
        List<String> errors = new ArrayList<>();
        for (Map.Entry<Boolean, List<String>> entry : map.entrySet()) {

            for (String s : entry.getValue()) {

                errors.add(s);

            }

        }
        return errors;
    }

    public boolean isValid() {
        //System.out.println("map keyset:"+map.keySet());
        return (boolean) map.keySet().toArray()[0];
    }
}



