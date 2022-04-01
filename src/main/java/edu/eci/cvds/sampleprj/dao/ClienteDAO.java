package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Cliente;

import java.util.ArrayList;
import java.util.Date;

public interface ClienteDAO {
    public void save(Cliente it) throws PersistenceException;
    public void add(int id , int idit , Date ini , Date fin)throws PersistenceException;
    public Cliente load(long doc) throws PersistenceException;
    public ArrayList<Cliente> loads() throws PersistenceException;
}
