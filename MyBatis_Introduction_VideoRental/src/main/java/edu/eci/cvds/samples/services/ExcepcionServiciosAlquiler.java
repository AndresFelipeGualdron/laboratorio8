package edu.eci.cvds.samples.services;

import edu.eci.cvds.sampleprj.dao.PersistenceException;

public class ExcepcionServiciosAlquiler extends Exception{
	
	public ExcepcionServiciosAlquiler(String string, PersistenceException ex) {
		ex.printStackTrace();
		//System.out.println(ex.getStackTrace());
	}

	public ExcepcionServiciosAlquiler(String string) {
	}

}
