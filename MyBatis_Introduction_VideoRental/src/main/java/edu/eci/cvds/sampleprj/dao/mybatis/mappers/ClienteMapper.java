package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;

/**
 *
 * @author 2106913
 */
public interface ClienteMapper {
    
    public Cliente consultarCliente(@Param("idcli") long id); 
    
    /**
     * Registrar un nuevo item rentado asociado al cliente identificado
     * con 'idc' y relacionado con el item identificado con 'idi'
     * @param id
     * @param idit
     * @param fechainicio
     * @param fechafin 
     */
    
    public void registrarCliente(@Param("cl") Cliente cliente);
    
    public void agregarItemRentadoACliente(@Param("idir") int idir,
    		@Param("idcli") int id, 
    		@Param("idit") int idit, 
    		@Param("fechainicio")Date fechainicio,
    		@Param("fechafin")Date fechafin);

    /**
     * Consultar todos los clientes
     * @return 
     */
    public List<Cliente> consultarClientes();
    
    /**
     * Consulta todos los items rentados de un cliente
     * @param id (id del cliente)
     * @return lista de todos los items rentados del cliente
     */
    public List<ItemRentado> consultarItemRentadoCliente(@Param("cliente") long cliente);
    
}
