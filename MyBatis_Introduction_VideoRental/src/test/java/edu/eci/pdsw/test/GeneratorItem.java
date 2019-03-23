package edu.eci.pdsw.test;

import org.junit.Test;
import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;

import java.util.Date;

public class GeneratorItem {
	public static Gen<Item>items(){
		return (ids().zip(nombres(),tarifas(),formatos(),tipos(),(id,nombre,tarifaxDia,formatoRenta,tipo)
				->new Item(id,nombre,tarifaxDia,formatoRenta,tipo)));
	}
	
	private static Gen<Integer> ids() {
		return integers().between(1, 11);
	}
	private static Gen<String> nombres(){
		return strings().basicLatinAlphabet().ofLengthBetween(1, 50);
	}
	private static Gen<Long> tarifas(){
		return longs().between(1, 20);
	}
	private static Gen<String> formatos(){
		return strings().basicLatinAlphabet().ofLengthBetween(1,20);
	}
	
	private static Gen<TipoItem> tipos(){
		return GeneratorTipoItem.tipoItem();
	}
}
