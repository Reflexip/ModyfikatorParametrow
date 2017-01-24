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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;


@ManagedBean
@SessionScoped
@Entity
public class Parameter{
    
//Fields------------------------------------------------------------------------    
    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;
    private String description;    
    private String parameterValue;  
    private ParameterType parameterType;
    @Transient
    private int parameterRadioChoose;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ParameterCategory> parameterCategories;
    

//Constructor-------------------------------------------------------------------
    
    public Parameter(){
        this.parameterCategories = new ArrayList();
        this.parameterType = ParameterType.TEXT;
        
   }
//Methodes----------------------------------------------------------------------    
    public void addParameterCategory(ParameterCategory parameterCategory){
        ParameterCategory parameterCat = parameterCategory;
        this.parameterCategories.add(parameterCat);
    }
    
    public void removeParameterCategory(ParameterCategory parameterCategory){
        for(ListIterator<ParameterCategory> iterator = this.parameterCategories.listIterator(); iterator.hasNext();){
           ParameterCategory parameterCat = iterator.next();
           if(parameterCat.equals(parameterCategory))
               iterator.remove();
           }        
        }
//Getters&Setters---------------------------------------------------------------

    public String getParameterValue() {
        return this.parameterValue;
    }

    public void setParameterValue(ParameterValue parameterValue) {        
        this.parameterValue = parameterValue.toString(this.parameterType);
    }
    
    public void setParameterValue(TempParameterValue tempParameterValue) {        
        this.parameterValue = tempParameterValue.toString(this.parameterType);
    }
    
    public ParameterType getParameterType() {
        return this.parameterType;
    }

    public void setParameterType(ParameterType parameterType) {
        this.parameterType = parameterType;
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
