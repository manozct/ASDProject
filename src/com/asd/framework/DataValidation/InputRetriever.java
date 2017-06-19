package com.asd.framework.DataValidation;

import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by manozct on 6/12/2017.
 */
public class InputRetriever {
    public static String retrieveText(Control control){
        if(control instanceof TextField){
            return ((TextField) control).getText();
        }
        else if(control instanceof DatePicker){
            LocalDate date=((DatePicker) control).getValue();
            if(date!=null){
                return date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            }

        }
        else if(control instanceof ComboBox){
            return (String) ((ComboBox) control).getSelectionModel().getSelectedItem();
        }
        else if(control instanceof RadioButton){
            ToggleGroup group=new ToggleGroup();
            if(group.getSelectedToggle()!=null){
                return group.getSelectedToggle().getUserData().toString();
            }

        }
        return null;
    }
    public static String getControlId(Control control){
        String controlId="";
        if(control instanceof TextField){
            return ((TextField) control).getId();
        }

        else if(control instanceof ComboBox){
            return (String) ((ComboBox) control).getId();
        }
        else if(control instanceof RadioButton){
           return (String)((RadioButton)control).getId();

        }
        else if(control instanceof DatePicker){
            return (String)((DatePicker)control).getId();
        }
        return controlId;

    }

}
