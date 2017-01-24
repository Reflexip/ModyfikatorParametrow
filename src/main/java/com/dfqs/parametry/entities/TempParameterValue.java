/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dfqs.parametry.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Dawid
 */
@SessionScoped
@ManagedBean
public class TempParameterValue{
    
    private String textValue;
    
    @Temporal(TemporalType.DATE)
    private Date dateValue;
    
    private int numberValue;

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public int getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(int numberValue) {
        this.numberValue = numberValue;
    }
    
   
    public String toString(ParameterType parameterType){
        if(parameterType == ParameterType.TEXT)
            return this.textValue;
        else if(parameterType == ParameterType.DATE){
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            return df.format(this.dateValue);
        }
        else if(parameterType == ParameterType.NUMBER)
            return Integer.toString(this.numberValue);
        return "no definded";    
    }
    
}
