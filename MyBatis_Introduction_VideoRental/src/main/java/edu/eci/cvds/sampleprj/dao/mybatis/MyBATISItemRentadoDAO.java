package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.ItemRentado;

public class MyBATISItemRentadoDAO implements ItemRentadoDAO{
	
	@Inject
	private ClienteMapper itemRentadoMapper;

	@Override
	public void save(ItemRentado it) throws PersistenceException {
		throw new UnsupportedOperationException("No hemos implementado el metodo save de MyBATISItemRentadoDAO");
		
	}

	@Override
	public List<ItemRentado> load(long id) throws PersistenceException {
		return this.itemRentadoMapper.consultarItemRentadoCliente(id);
		//throw new UnsupportedOperationException("No hemos implementado el metodo load de MyBATISItemRentadoDAO");
		//return null;
	}

}
