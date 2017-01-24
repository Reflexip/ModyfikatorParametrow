package com.dfqs.parametry.services;

import com.dfqs.parametry.entities.Parameter;
import com.dfqs.parametry.entities.ParameterCategory;
import com.dfqs.parametry.entities.ParameterValue;
import com.dfqs.parametry.entities.TempParameter;
import com.dfqs.parametry.entities.TempParameterValue;
import java.util.List;
import java.util.ListIterator;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

@SessionScoped
@ManagedBean
public final class DatabaseManager{
    private static final String PU = "parametryPU";
    
    @ManagedProperty(value = "#{tempParameter}")
    private TempParameter tempParameter;
    
    @ManagedProperty(value = "#{tempParameterValue}")
    private TempParameterValue tempParameterValue;
    
    private List<Parameter> parametersFromDB;
    
    public DatabaseManager(){
        this.parametersFromDB = this.getAllParameters();
    }
    
    public static void addParameterToDB(Parameter parameter, ParameterValue parameterValue){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PU);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        parameter.setParameterValue(parameterValue);
        
        parameter.getParameterCategories().forEach((pCategory) -> {
            entityManager.persist(pCategory);
        });        
        
        entityManager.persist(parameter);
        
        entityManager.getTransaction().commit();        
        entityManager.close();
        entityManagerFactory.close();   
        
        clearSession();
        }
    
    public static void removeParameterFromDB(Parameter parameter){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PU);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();        
            Parameter parameterFromDB = entityManager.find(Parameter.class, parameter.getId());
            entityManager.remove(parameterFromDB);                
        entityManager.getTransaction().commit();        
        entityManager.close();
        entityManagerFactory.close();  
        
        clearSession();
    
    }
    public void loadParameterToEdit(Parameter parameter){
        tempParameter.setId(parameter.getId());    
        tempParameter.setDescription(parameter.getDescription());    
        tempParameter.setName(parameter.getName());    
        tempParameter.setParameterCategories(parameter.getParameterCategories());    
        tempParameter.setParameterRadioChoose(parameter.getParameterRadioChoose());    
        tempParameter.setParameterValue(parameter.getParameterValue());    
    }
    
    public void changeParameter(){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PU);
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            Parameter parameterFromDB = entityManager.find(Parameter.class, tempParameter.getId());  

            entityManager.getTransaction().begin();

            if(parameterFromDB != null){
            parameterFromDB.setName(tempParameter.getName());   
            if(parameterFromDB.getDescription() == null || !parameterFromDB.getDescription().equals(tempParameter.getDescription()))
                parameterFromDB.setDescription(tempParameter.getDescription()); 
            parameterFromDB.setParameterCategories(tempParameter.getParameterCategories());    
            parameterFromDB.setParameterRadioChoose(tempParameter.getParameterRadioChoose());    
            if(tempParameterValue.getDateValue() != null 
                    || tempParameterValue.getTextValue() != null 
                    && tempParameterValue.getTextValue().length() > 0
                    || tempParameterValue.getNumberValue() != 0){
                parameterFromDB.setParameterType(tempParameter.getParameterType());
                parameterFromDB.setParameterValue(tempParameterValue);
            }

            for(ListIterator<ParameterCategory> iterator = parameterFromDB.getParameterCategories().listIterator(); iterator.hasNext();){
               ParameterCategory parameterCat = iterator.next();
               entityManager.merge(parameterCat);
            }        
            entityManager.merge(parameterFromDB);

            entityManager.getTransaction().commit();        
            entityManager.close();
            entityManagerFactory.close();  
            clearSession();  
            }
        
    }
   
  
    public List<Parameter> getAllParameters(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PU);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        TypedQuery<Parameter> query = entityManager.createQuery("select i from Parameter i", Parameter.class);
        List<Parameter> parameters = query.getResultList();
        
        entityManager.close();
        entityManagerFactory.close();
        return parameters;
    }
    
    public static void clearSession(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)facesContext.getExternalContext().getSession(false);
        session.invalidate();        
    }

    public List<Parameter> getParametersFromDB() {
        return parametersFromDB;
    }

    public void setParametersFromDB(List<Parameter> parametersFromDB) {
        this.parametersFromDB = parametersFromDB;
    }
    
    public TempParameter getTempParameter() {
        return tempParameter;
    }

    public void setTempParameter(TempParameter tempParameter) {
        this.tempParameter = tempParameter;
    }

    public TempParameterValue getTempParameterValue() {
        return tempParameterValue;
    }

    public void setTempParameterValue(TempParameterValue tempParameterValue) {
        this.tempParameterValue = tempParameterValue;
    }
    
}
