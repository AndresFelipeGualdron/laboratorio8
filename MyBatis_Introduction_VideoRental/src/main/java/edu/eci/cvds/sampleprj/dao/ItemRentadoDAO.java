package edu.eci.cvds.sampleprj.dao;

import java.sql.Date;
import java.util.List;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;

public interface ItemRentadoDAO {
	
	public void save(ItemRentado ir) throws PersistenceException;

	public List<ItemRentado> load(long id) throws PersistenceException;

}
