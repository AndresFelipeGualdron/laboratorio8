package edu.eci.pdsw.test;


import org.junit.Test;
import org.quicktheories.core.Gen;

import edu.eci.cvds.samples.entities.TipoItem;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;

public class GeneratorTipoItem {
	public static Gen<TipoItem> tipoItem(){
		return (ids().zip(descripciones(), (id,descripcion)-> new TipoItem(id,descripcion)));
		
	}
	private static Gen<Integer> ids(){
		return integers().between(1,11);
	}
	private static Gen<String> descripciones(){
		return strings().basicLatinAlphabet().ofLengthBetween(1, 20);
	}	

}
