package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.List;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.cvds.samples.entities.TipoItem;

public class MyBATISTipoItemDAO implements TipoItemDAO{
	
	@Inject
	private TipoItemMapper tim;

	@Override
	public void save(TipoItem it) throws PersistenceException {
		throw new UnsupportedOperationException("No hemos implementado el metodo save de MyBATISTipoItemDAO");
		
	}

	@Override
	public TipoItem load(int id) throws PersistenceException {
		return tim.consultarTipoItem(id);
	}

	@Override
	public List<TipoItem> load() throws PersistenceException {
		return tim.consultarTipoItems();
	}

}
