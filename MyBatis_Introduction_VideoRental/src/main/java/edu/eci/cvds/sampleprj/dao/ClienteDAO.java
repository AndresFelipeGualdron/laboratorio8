package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.Cliente;

public interface ClienteDAO {
	public void save(Cliente it) throws PersistenceException;

	public Cliente load(long id) throws PersistenceException;
	
	public List<Cliente> load() throws PersistenceException;
}
