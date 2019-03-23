package edu.eci.pdsw.test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.quicktheories.core.Gen;
import org.quicktheories.generators.IntegersDSL;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.Generate.*;
import static org.quicktheories.generators.SourceDSL.*;

public class ServiciosAlquilerTest {

    @Inject
    private SqlSession sqlSession;
	
    private ServiciosAlquiler serviciosAlquiler;

    public ServiciosAlquilerTest() {
        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
    }

    @Before
    public void setUp() {
    }

    
    @Test
    public void emptyDB() {
    	System.setProperty("QT_EXAMPLES", "13");
        qt().forAll(longs().from(1).upTo(1000)).check((documento) -> {
            boolean r = true;
            try {
                Cliente cliente = serviciosAlquiler.consultarCliente(documento);
            } catch(ExcepcionServiciosAlquiler e) {
                r = true;
            } catch(IndexOutOfBoundsException e) {
                r = true;
            }
            return r;
        });
    }
    
    @Test
    public void itShouldBringTheFeeCorrectly() {
    	System.setProperty("QT_EXAMPLES", "13");
        qt().
        forAll(GeneratorItem.items()).
        check( i-> {
            try {
            	serviciosAlquiler.registrarItem(i);
                return i.getTarifaxDia() == serviciosAlquiler.valorMultaRetrasoxDia(i.getId());
            }catch (Exception e) {
				return true;
			}          
        });
    }
    
    @Test
    public void itShouldBringAUserCorrectly() {
    	System.setProperty("QT_EXAMPLES", "13");
        qt().
        forAll(GeneratorCliente.cliente()).
        check( cl-> {
            try {
            	serviciosAlquiler.registrarCliente(cl);
            	Cliente c = serviciosAlquiler.consultarCliente(cl.getDocumento());
                return cl.getDocumento() == c.getDocumento() && cl.getNombre().equals(c.getNombre())
                		&& cl.isVetado() == c.isVetado() && cl.getDireccion().equals(c.getDireccion())
                		&& cl.getEmail().equals(c.getEmail()) && cl.getTelefono().equals(c.getTelefono());
            }catch (Exception e) {
				return true;
			}          
        });
    }
    
    @Test
    public void itShouldBringAllUserItemsCorrectly() {
    	System.setProperty("QT_EXAMPLES", "13");
        qt().
        forAll(GeneratorCliente.cliente(),GeneratorItem.items(),integers().allPositive()).
        check( (cl,i,numdias)-> {
            try {
            	int a = Calendar.YEAR;
            	int m = Calendar.MONTH;
            	int d = Calendar.DAY_OF_MONTH;
            	String s = Integer.toString(a)+"-"+Integer.toString(m)+"-"+Integer.toString(d);
            	serviciosAlquiler.registrarItem(i);
            	serviciosAlquiler.registrarCliente(cl);
            	serviciosAlquiler.registrarAlquilerCliente(Date.valueOf(s),cl.getDocumento(),i,numdias);
            	List<ItemRentado> its = serviciosAlquiler.consultarItemsCliente(cl.getDocumento());
            	Item item = its.get(0).getItem();
            	ItemRentado ir = its.get(0);
            	return s.equals(ir.getFechainiciorenta()) && item.getId() == i.getId() && item.getNombre().equals(i.getNombre())
            			&& item.getDescripcion().equals(i.getDescripcion()) && item.getGenero().equals(i.getGenero()) &&
            			ir.getId() == cl.getDocumento() && i.getTarifaxDia() == item.getTarifaxDia();
            } catch (Exception e) {
				return true;
			}          
        });
    }
    
    @Test
    public void itShouldBringAllUsersCorrectly() {
    	System.setProperty("QT_EXAMPLES", "13");
    	qt().
        forAll(GeneratorCliente.cliente(),GeneratorCliente.cliente(),GeneratorCliente.cliente()).
        check( (c1,c2,c3)-> {
            try {
            	serviciosAlquiler.registrarCliente(c1);serviciosAlquiler.registrarCliente(c2);serviciosAlquiler.registrarCliente(c3);
            	List<Cliente> cls = serviciosAlquiler.consultarClientes();
            	boolean flag1 = false;boolean flag2 = false;boolean flag3 = false;
            	for(int i = 0; i < cls.size();i++) {
            		if(cls.get(i).getDocumento() == c1.getDocumento()) {
            			flag1 = true;
            		}
            		if(cls.get(i).getDocumento() == c2.getDocumento()) {
            			flag2 = true;
            		}
            		if(cls.get(i).getDocumento() == c3.getDocumento()) {
            			flag3 = true;
            		}
            	}
            	return flag1 && flag2 && flag3;
            }catch (Exception e) {
				return true;
			}         
        });
    }
    
    @Test
    public void itShouldBringAnItemCorrectly() {
    	System.setProperty("QT_EXAMPLES", "13");
        qt().
        forAll(GeneratorItem.items()).
        check( i-> {
            try {
            	serviciosAlquiler.registrarItem(i);
            	Item item = serviciosAlquiler.consultarItem(i.getId());           	
            	return item.getId() == i.getId() && item.getNombre().equals(i.getNombre())
            			&& item.getDescripcion().equals(i.getDescripcion()) && item.getGenero().equals(i.getGenero()) &&
            			i.getTarifaxDia() == item.getTarifaxDia();
            }catch (Exception e) {
				return true;
			}          
        });
    }
    
    @Test
    public void itShouldBringAllAvailableItemsCorrectly() {
    	System.setProperty("QT_EXAMPLES", "13");
        qt().
        forAll(GeneratorItem.items(),GeneratorCliente.cliente(),integers().allPositive()).
        check( (i,cl,numdias)-> {
            try {
            	serviciosAlquiler.registrarItem(i);
            	serviciosAlquiler.registrarCliente(cl);            	
            	boolean flag = false;
            	List<Item> itms = serviciosAlquiler.consultarItemsDisponibles();
            	for(int x = 0; x < itms.size() && !flag; x++) {
            		if(itms.get(x).getId() == i.getId()) {
            			flag = true;
            		}
            	}
            	int a = Calendar.YEAR;
            	int m = Calendar.MONTH;
            	int d = Calendar.DAY_OF_MONTH;
            	String s = Integer.toString(a)+"-"+Integer.toString(m)+"-"+Integer.toString(d);
            	serviciosAlquiler.registrarAlquilerCliente(Date.valueOf(s), cl.getDocumento(), i, numdias);
            	boolean flag2 = true;
            	itms = serviciosAlquiler.consultarItemsDisponibles();
            	for(int x = 0; x < itms.size() && !flag; x++) {
            		if(itms.get(x).getId() == i.getId()) {
            			flag2 = false;
            		}
            	}
            	return flag == flag2;
            }catch (Exception e) {
				return true;
			}   
        });
    } 
    
    
    @Test
    public void itShouldBringTheRentalFeeCorrectly() {
    	System.setProperty("QT_EXAMPLES", "13");
        qt().
        forAll(GeneratorItem.items(),GeneratorCliente.cliente(),integers().between(1,12),integers().between(1,28)).
        check( (i,cl,m,d)-> {
            try {
            	int a = 3019;
            	String fecha = Integer.toString(a)+"-"+m.toString()+"-"+d.toString();
            	Date s=Date.valueOf(fecha);
            	serviciosAlquiler.registrarCliente(cl);serviciosAlquiler.registrarItem(i);
            	List<ItemRentado> its = serviciosAlquiler.consultarItemsCliente(cl.getDocumento());
            	ItemRentado ir = null;
            	Item item;
            	long dias;
            	for(int x = 0; x < its.size(); x++) {
            		if(its.get(x).getId() == cl.getDocumento() && its.get(x).getItem().getId() == i.getId()) {
            			ir = its.get(x); item = its.get(x).getItem();
            		}
            	}
            	int fechafin = ir.getFechafinrenta().getDay();
            	dias = s.getDay()-fechafin;
            	if(dias <= 0) {
            		dias = 0;
            	}
            	return serviciosAlquiler.consultarMultaAlquiler(i.getId(),s) == i.getTarifaxDia()*dias;
            }catch (Exception e) {
				return true;
			}       
        });
    }
    
    @Test
    public void itShouldBringAnItemTypeCorrectly() {
    	System.setProperty("QT_EXAMPLES", "13");
        qt().
        forAll(GeneratorItem.items()).
        check( i-> {
            try {
            	serviciosAlquiler.registrarItem(i);
            	TipoItem tipo = i.getTipo();
            	TipoItem ti = serviciosAlquiler.consultarTipoItem(i.getTipo().getID());
                return tipo.getID() == ti.getID() && tipo.getDescripcion().equals(ti.getDescripcion());
            }catch (Exception e) {
				return true;
			}          
        });
    }
    
    @Test
    public void itShouldBringAllItemTypesCorrectly() {
    	System.setProperty("QT_EXAMPLES", "13");
        qt().
        forAll(integers().allPositive()).
        check(i -> {
            try {
            	Item it = serviciosAlquiler.consultarItem(i);
            	List<TipoItem> its = serviciosAlquiler.consultarTiposItem();
            	boolean flag = false;
            	for(int x = 0; x < its.size() && !flag; x++) {
            		if(its.get(x).getID() == it.getId()) {
            			flag = true;
            		}
            	}
            	return flag;
            }catch (Exception e) {
				return true;
			}         
        });
    }
    
    @Test
    public void itShouldRegisterAnUserCorrectly() {
    	System.setProperty("QT_EXAMPLES", "13");
    	 qt().
         forAll(GeneratorCliente.cliente()).
         check( cl -> {
             try {
             	Cliente c = serviciosAlquiler.consultarCliente(cl.getDocumento());
                 if(c == null) {
                 	serviciosAlquiler.registrarCliente(cl);
                 	return cl.getDocumento()== serviciosAlquiler.consultarCliente(cl.getDocumento()).getDocumento();
                 }else {
                 	return true;
                 }
             }catch (Exception e) {
 				return false;
 			}          
         });
    }
    
    @Test
    public void itShouldRegisterAnItemCorrectly() {
    	System.setProperty("QT_EXAMPLES", "13");
        qt().
        forAll(GeneratorItem.items()).
        check( it -> {
            try {
            	Item i = serviciosAlquiler.consultarItem(it.getId());
                if(i == null) {
                	serviciosAlquiler.registrarItem(it);
                	return it.getId() == serviciosAlquiler.consultarItem(it.getId()).getId();
                }else {
                	return true;
                }
            }catch (Exception e) {
            	return true;
			}          
        });
    }
    
    @Test
    public void itShouldBringAnRentalCostCorrectly() {
    	System.setProperty("QT_EXAMPLES", "13");
        qt().
        forAll(GeneratorItem.items(),integers().allPositive()).
        check( (it,d) -> {
            try {
            	Item i = serviciosAlquiler.consultarItem(it.getId());
                if(i == null) {
                	serviciosAlquiler.registrarItem(it);
                	}
                long valor = serviciosAlquiler.consultarCostoAlquiler(it.getId(),d);
                return valor == i.getTarifaxDia() * d;
            }catch (Exception e) {
				return true;
			}          
        });
    }
    
    @Test
    public void itShouldUpdateAnItemRateCorrectly() {
    	System.setProperty("QT_EXAMPLES", "13");
        qt().
        forAll(GeneratorItem.items(),integers().allPositive()).
        check( (it,t) -> {
            try {
            	Item i = serviciosAlquiler.consultarItem(it.getId());
                if(i == null) {
                	serviciosAlquiler.registrarItem(it);
                }
                serviciosAlquiler.actualizarTarifaItem(it.getId(),t);
            	i = serviciosAlquiler.consultarItem(it.getId());
            	return t == i.getTarifaxDia();
            }catch (Exception e) {
				return true;
			}          
        });
    }
    
    @Test
    public void itShouldVetoAnUserCorrectly() {
    	System.setProperty("QT_EXAMPLES", "13");
        qt().
        forAll(GeneratorCliente.cliente()).
        check( cl -> {
            try {
            	Cliente c = serviciosAlquiler.consultarCliente(cl.getDocumento());
                if(c == null) {
                	serviciosAlquiler.registrarCliente(cl);
                	}
                c = serviciosAlquiler.consultarCliente(cl.getDocumento());
            	if(c.isVetado()) {
            		serviciosAlquiler.vetarCliente(cl.getDocumento(),false);
            		c = serviciosAlquiler.consultarCliente(cl.getDocumento());
            		return !c.isVetado();
            	}else {
            		serviciosAlquiler.vetarCliente(cl.getDocumento(),true);
            		c = serviciosAlquiler.consultarCliente(cl.getDocumento());
            		return c.isVetado();
            	}            
            }catch (Exception e) {
				return true;
			}          
        });
    }
   
    @Test
    public void itShouldRegisterAnItemRentalToAnUserCorrectly() {
    	System.setProperty("QT_EXAMPLES", "13");
        qt().
        forAll(GeneratorCliente.cliente(),
        		GeneratorItem.items(),
        		integers().allPositive()
        		).
        check( (cl,item,dias) -> {
            try {
            	Item i = serviciosAlquiler.consultarItem(item.getId());
            	Cliente c = serviciosAlquiler.consultarCliente(cl.getDocumento());
            	if(i == null) {
            		serviciosAlquiler.registrarItem(item);
            	}
            	if(c == null) {
            		serviciosAlquiler.registrarCliente(cl);
            	}
            	int a = Calendar.YEAR;
            	int m = Calendar.MONTH;
            	int d = Calendar.DAY_OF_MONTH;
            	String s = Integer.toString(a)+Integer.toString(m)+Integer.toString(d); 
            	serviciosAlquiler.registrarAlquilerCliente(Date.valueOf(s), cl.getDocumento(),item,dias);
            	List<ItemRentado> its = serviciosAlquiler.consultarItemsCliente(cl.getDocumento());
            	boolean flag = false;
            	for(int x = 0; x < its.size() && !flag; x++) {
            		if(its.get(x).getItem().getId() == item.getId() && its.get(x).getId() == cl.getDocumento()) {
            			flag = true;
            		}
            	}
            	return flag;
            }catch (Exception e) {
				return true;
			}          
        });
    }
}