package com.dfqs.parametry.entities;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class TempParameterCategory{
   
    private int id;
   
    private String name;

    private List<Parameter> parameters;

    public TempParameterCategory(){
    }
    public TempParameterCategory(String name){
        this.name = name;
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
        this.name = name.toUpperCase();
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
}