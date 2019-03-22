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
}