package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date;

public class MyBATISItemDAO implements ItemDAO{

  @Inject
  private ItemMapper itemMapper;

  @Override
  public void save(Item it) throws PersistenceException{
      itemMapper.insertarItem(it);
  }

  @Override
  public Item load(int id) throws PersistenceException {
      return itemMapper.consultarItem(id);
  }

	@Override
	public int consultarTarifaxDia(int itemId) throws PersistenceException{
			return itemMapper.consultarTarifaxDia(itemId);
	}

	@Override
	public List<Item> ItemsDisponibles() throws PersistenceException {
		try {
			return itemMapper.consultarItemsDisponibles();
		}catch(org.apache.ibatis.exceptions.PersistenceException e) {
			throw new PersistenceException("Error al consultar los items disponibles",e);
		}
	}

	@Override
	public void actualizarTarifaItem(int id, long tarifa) throws PersistenceException {
		itemMapper.actualizarTarifaItem(id,tarifa);
	}

	@Override
	public long consultarMultaAlquiler(int iditem, java.util.Date date) throws PersistenceException {
		return itemMapper.consultarMultaAlquiler(iditem, date);
	}
}