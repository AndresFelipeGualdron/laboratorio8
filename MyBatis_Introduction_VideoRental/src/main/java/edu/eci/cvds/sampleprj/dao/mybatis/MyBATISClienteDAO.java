package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.services.client.MyBatisExample;

public class MyBATISClienteDAO implements ClienteDAO{
	
	@Inject
	private ClienteMapper cm;

	@Override
	public void save(Cliente it) throws PersistenceException {
		cm.registrarCliente(it);
		
	}

	@Override
	public Cliente load(long id) throws PersistenceException {
		return cm.consultarCliente(id);
	}

	@Override
	public long consultarCostoAlquiler(int iditem, int numdias) throws PersistenceException {
		cm.consultarCostoAlquiler(iditem, numdias);
		return 0;
	}
	
	public List<Cliente> load() throws PersistenceException {
		return cm.consultarClientes();
	}

}
