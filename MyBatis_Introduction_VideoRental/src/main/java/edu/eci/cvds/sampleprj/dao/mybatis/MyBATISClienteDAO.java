package edu.eci.cvds.sampleprj.dao.mybatis;

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
	private ClienteDAO clienteDAO;

	@Override
	public void save(Cliente it) throws PersistenceException {
		throw new UnsupportedOperationException("No hemos implementado el metodo save de MyBATISClienteDAO");
		
	}

	@Override
	public Cliente load(long id) throws PersistenceException {
		MyBatisExample ex = new MyBatisExample();
		SqlSessionFactory sessionfact = ex.getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();
		ClienteMapper cm=sqlss.getMapper(ClienteMapper.class);
		System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKK");
		System.out.println(cm.consultarCliente(id));
		return cm.consultarCliente(id);
	}

}
