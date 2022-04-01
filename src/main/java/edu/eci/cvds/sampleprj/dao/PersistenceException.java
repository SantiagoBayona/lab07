package edu.eci.cvds.sampleprj.dao;

public class PersistenceException extends Exception {

    public PersistenceException(String msm, Exception e){
        super(msm,e);
    }

}
