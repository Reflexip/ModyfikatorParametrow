/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dfqs.parametry.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Dawid
 */
@SessionScoped
@ManagedBean
public class TempParameter{
    
    private int id;
    
    private String name;
    private String description;    
    private String parameterValue;  
    private ParameterType parameterType;

    private int parameterRadioChoose;

    private List<ParameterCategory> parameterCategories;
    
    public TempParameter(){
        this.parameterCategories = new ArrayList();
        this.parameterType = ParameterType.TEXT;        
    }    
    
    public void addParameterCategory(ParameterCategory parameterCategory){
        ParameterCategory parameterCat = parameterCategory;
        this.parameterCategories.add(parameterCat);
    }
    
    public void addParameterCategory(TempParameterCategory tempParameterCategory){
        ParameterCategory parameterCat = new ParameterCategory();
        parameterCat.setId(tempParameterCategory.getId());
        parameterCat.setName(tempParameterCategory.getName());
        parameterCat.setParameters(tempParameterCategory.getParameters());
        this.parameterCategories.add(parameterCat);
    }
    
    public void removeParameterCategory(ParameterCategory parameterCategory){
        for(ListIterator<ParameterCategory> iterator = this.parameterCategories.listIterator(); iterator.hasNext();){
           ParameterCategory parameterCat = iterator.next();
           if(parameterCat.equals(parameterCategory))
               iterator.remove();
           }        
        }

    public String getParameterValue() {
        return this.parameterValue;
    }

    public void setParameterValue(ParameterValue parameterValue) {        
        this.parameterValue = parameterValue.toString(this.parameterType);
    }
    
    public void setParameterValue(String parameterValue) {        
        this.parameterValue = parameterValue;
    }

    
    public ParameterType getParameterType() {
        return this.parameterType;
    }

    public int getParameterRadioChoose() {
        return parameterRadioChoose;
    }
    
    public void setParameterRadioChoose(int radioChoose) {
        this.parameterRadioChoose = radioChoose;
        switch (this.parameterRadioChoose) {
            case 0:
                this.parameterType = ParameterType.TEXT;
                break;
            case 1:
                this.parameterType = ParameterType.DATE;
                break;
            case 2:
                this.parameterType = ParameterType.NUMBER;
                break;
            default:
                this.parameterType = ParameterType.TEXT;
                break;
        }
    }

    public List<ParameterCategory> getParameterCategories() {
        return parameterCategories;
    }

    public void setParameterCategories(List<ParameterCategory> parameterCategories) {
        this.parameterCategories = parameterCategories;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }   
    
}