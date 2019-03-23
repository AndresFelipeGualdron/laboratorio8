package edu.eci.cvds.sampleprj.dao;

import java.sql.Date;
import java.util.List;

import edu.eci.cvds.samples.entities.Item;

public interface ItemDAO{

   public void save(Item it) throws PersistenceException;

   public Item load(int id) throws PersistenceException;
   
   public int consultarTarifaxDia(int itemId) throws PersistenceException;
   
   public List<Item> ItemsDisponibles() throws PersistenceException;
   
   public void actualizarTarifaItem(int id, long tarifa) throws PersistenceException;
   
   public long consultarMultaAlquiler(int iditem, java.util.Date date) throws PersistenceException;

}