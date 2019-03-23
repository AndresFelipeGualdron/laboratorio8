package edu.eci.pdsw.test;
import org.junit.Test;
import org.quicktheories.core.Gen;

import edu.eci.cvds.samples.entities.Cliente;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;


public class GeneratorCliente {
	public static Gen<Cliente> cliente(){
		return (nombres().zip(documentos(),telefonos(),direcciones(),emails(),(nombre,documento,telefono,direccion,email)-> new Cliente(nombre,documento,telefono,direccion,email)));
	}
	
	
	private static Gen<Long> documentos() {
		return longs().between(0, 50000);		
	}
	private static Gen<String> nombres(){
		return strings().basicLatinAlphabet().ofLengthBetween(1, 50);
		
	}
	private static Gen<String> telefonos() {
		return strings().basicLatinAlphabet().ofLengthBetween(1, 20);		
	}
	private static Gen<String> direcciones() {
		return strings().basicLatinAlphabet().ofLengthBetween(1, 150);		
	}
	private static Gen<String> emails() {
		return strings().basicLatinAlphabet().ofLengthBetween(1, 150);		
	}
	

}
