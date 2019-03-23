package edu.eci.cvds.sampleprj.dao.mybatis.mappers;


import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.TipoItem;

public interface TipoItemMapper {
    
    
    //public List<TipoItem> getTiposItems();
    
    public TipoItem consultarTipoItem(@Param("idti")int id);

	public List<TipoItem> consultarTipoItems();
    
    //public void registrarTipoItem(@Param("tipo") TipoItem tipoItem);

}
