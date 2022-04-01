package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;

import java.util.Date;
import java.util.List;

public interface ClienteDAO {
    public void agregarItemRentadoACliente(long id, int idit, Date fechainicio, Date fechafin)  throws PersistenceException ;
    public void add(Cliente c) throws PersistenceException;
    public Cliente load(long doc) throws PersistenceException;
    public List<Cliente> loads() throws PersistenceException;
    public List<ItemRentado> consultarItemsCliente(long idcliente) throws PersistenceException;
    public void vetarCliente(long doc, boolean estado) throws PersistenceException;
}
