package edu.eci.cvds.samples.services;

import edu.eci.cvds.sampleprj.dao.PersistenceException;

public class ExcepcionServiciosAlquiler extends Exception{
	
	public ExcepcionServiciosAlquiler(String string, PersistenceException ex) {
		System.out.println(ex.getMessage());
	}

	public ExcepcionServiciosAlquiler(String string) {
	}

}
