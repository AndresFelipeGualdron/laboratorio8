package edu.eci.cvds.sampleprj.dao.mybatis;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;

public class MyBATISClienteDAO implements ClienteDAO{

	@Override
	public void save(Cliente it) throws PersistenceException {
		throw new UnsupportedOperationException("No hemos implementado el metodo save de MyBATISClienteDAO");
		
	}

	@Override
	public Cliente load(int id) throws PersistenceException {
		throw new UnsupportedOperationException("No hemos implementado el metodo load de MyBATISClienteDAO");
	}

}
