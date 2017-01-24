/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dfqs.parametry.entities;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Dawid
 */
@RequestScoped
@ManagedBean
@Entity
public class ParameterCategory {
    
   @Id
   @Column(name = "id", columnDefinition = "serial")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   
   private String name;
  
   @ManyToMany
   private List<Parameter> parameters;

   public ParameterCategory(){
   }
   public ParameterCategory(String name){
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
