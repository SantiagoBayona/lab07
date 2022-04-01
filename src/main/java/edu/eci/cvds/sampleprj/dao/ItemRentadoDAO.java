package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.ItemRentado;

import java.util.List;

public interface ItemRentadoDAO {


    public ItemRentado loadItem(int id) throws PersistenceException;

    public List<ItemRentado> loadItems() throws PersistenceException;

}