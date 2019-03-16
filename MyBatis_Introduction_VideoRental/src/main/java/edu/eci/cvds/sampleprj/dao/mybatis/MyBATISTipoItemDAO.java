package edu.eci.cvds.sampleprj.dao.mybatis;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.samples.entities.TipoItem;

public class MyBATISTipoItemDAO implements TipoItemDAO{

	@Override
	public void save(TipoItem it) throws PersistenceException {
		throw new UnsupportedOperationException("No hemos implementado el metodo save de MyBATISTipoItemDAO");
		
	}

	@Override
	public TipoItem load(int id) throws PersistenceException {
		throw new UnsupportedOperationException("No hemos implementado el metodo load de MyBATISTipoItemDAO");
	}

}
