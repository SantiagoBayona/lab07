package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.TipoItem;

import java.util.List;

public interface TipoItemDAO {

    public void save(TipoItem tipoitem) throws PersistenceException;

    public TipoItem loadTipo(int id) throws PersistenceException;

    public List<TipoItem> loadTipos() throws PersistenceException;
}