/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cvds.samples.services.client;



import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import edu.eci.cvds.samples.services.impl.ServiciosAlquilerImpl;

/**
 *
 * @author hcadavid
 */
public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     * @throws ExcepcionServiciosAlquiler 
     */
    public static void main(String args[]) throws SQLException, ExcepcionServiciosAlquiler {
        SqlSessionFactory sessionfact = getSqlSessionFactory();
        SqlSession sqlss = sessionfact.openSession();
        
        //SE DEBE REVISAR QUE LOS PARAMETRO FK NO ESTEN REPETIDOS O VILARIA LA CONDICIÓN DE LLAVE PRIMARIA
        /*También se debe tener en cuenta que el id de la tabla VI_ITEMRENTADO no está automatizado, por lo tanto se debe ingresar
        uno que no este ya en la tabla*/
        
        
        
        //cm.agregarItemRentadoACliente(2132219, 123456, 5, new Date(), new Date());
        
        
        
        
        ServiciosAlquilerFactory fa = ServiciosAlquilerFactory.getInstance();
        ServiciosAlquiler ser = fa.getServiciosAlquiler();
        
        /*
         * se debe tener en cuenta las restricciones de primarias y foraneas
         * */
        
        /**Cliente cl = new Cliente("Alejandro",2418907,"3156854826","cra 54 16","correo@gmail.com");
        ser.registrarCliente(cl);*/
        
        
        /*
         * se debe tener en cuenta las restricciones de primarias y foraneas
         * */
        /**TipoItem tipo = new TipoItem(2,"Accion");
        Item e = new Item(tipo, 1000, "item1000", "descripcion",Date.from(Instant.now()), 600, "formato renta", "genero");
        ser.registrarItem(e);*/
        
        System.out.println(ser.consultarCliente(4));
        System.out.println(ser.consultarItem(3));
        System.out.println(ser.consultarTipoItem(1));
        
        /*
         * Agregando un cliente. Se debe cambiar el id (primaria) para respetar la condiciones de integridad de la base de datos
         */
        
        TipoItem tipo = new TipoItem(1,"Videojuego");
        Item i = new Item(tipo,900, "NOMBRE", "descripcion", Date.from(Instant.now()), 500, "formato renta", "genero");
        //ser.registrarItem(i);
        
        /*
         * CONSULTANDO TODOS LOS ITEMS RENTADOS DEL CLIENTE 123465
         */
        System.out.println(ser.consultarItemsCliente(123456));
        
        
        //System.out.println(ser.consultarItemsDisponibles());
        
        String string = "5818-04-20";
        String string2 = "5818-04-28";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        Date date=Date.valueOf(string);
        Date date2=Date.valueOf(string2);
        ser.registrarAlquilerCliente(date, 2418907, i, 15);
        System.out.println(ser.consultarItemsCliente(2418907));
        sqlss.commit();
        sqlss.close();

        
        
    }


}
