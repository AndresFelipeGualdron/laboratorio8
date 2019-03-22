package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.ItemRentado;

public interface ItemRentadoDAO {
	
	public void save(ItemRentado it) throws PersistenceException;

	public List<ItemRentado> load(long id) throws PersistenceException;

}
