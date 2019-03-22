package edu.eci.cvds.samples.services.impl;



import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;

import java.sql.Date;
import java.util.List;

@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquiler {

   @Inject
   private ItemDAO itemDAO;
   @Inject
   private ClienteDAO clenteDAO;
   @Inject
   private TipoItemDAO tipoItemDAO;
   @Inject
   private ItemRentadoDAO itemRentadoDAO;

   @Override
   public int valorMultaRetrasoxDia(int itemId) throws ExcepcionServiciosAlquiler{
       try {
    	   Item i = itemDAO.load(itemId);
    	   return i.getId();
    	   //Item i = itemDAO.consultarTarifaxDia(itemId);
       }catch(PersistenceException e) {
    	   throw new ExcepcionServiciosAlquiler("Error al consultar el valor de la multa del item: "+itemId,e);
       }
   }

   @Override
   public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
	   try{
		   return clenteDAO.load(docu);
	   } catch(PersistenceException e) {
		   throw new ExcepcionServiciosAlquiler("Error al consultar el cliente "+docu,e);
	   }
   }

   @Override
   public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
       try {
    	   return itemRentadoDAO.load(idcliente);
       }catch(PersistenceException e) {
    	   throw new ExcepcionServiciosAlquiler("No se pudo consultar el item del cliente"+idcliente,e);
       }
   }

   @Override
   public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
       try {
    	   return clenteDAO.load();
       }catch(PersistenceException e) {
    	   throw new ExcepcionServiciosAlquiler("No se pudo consultar los clientes",e);
       }
   }

   @Override
   public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
       try {
           return itemDAO.load(id);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el item "+id,ex);
       }
   }

   @Override
   public List<Item> consultarItemsDisponibles() throws ExcepcionServiciosAlquiler{
       try {
    	   return itemDAO.consultarItemsDisponibles();
       }catch(PersistenceException e) {
    	   throw new ExcepcionServiciosAlquiler("Error al consultar items disponibles",e);
       }
   }

   @Override
   public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
       try {
    	   return tipoItemDAO.load(id);
       }catch (PersistenceException e) {
    	   throw new ExcepcionServiciosAlquiler("Error al consultar el tipo item "+id,e);
       }
   }

   @Override
   public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }
   
   
   //GUALDRON HASTA ACÁ
   @Override
   public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
       try {
    	   clenteDAO.save(c);
       }catch (PersistenceException e) {
    	   throw new ExcepcionServiciosAlquiler("No se pudo registrar el cliente");
       }
   }

   @Override
   public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }
   
   @Override
   public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
        try {
        	itemDAO.save(i);
        }catch(PersistenceException e) {
        	throw new ExcepcionServiciosAlquiler("No se pudo registrar el item.");
        }
   }

   @Override
   public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
}
