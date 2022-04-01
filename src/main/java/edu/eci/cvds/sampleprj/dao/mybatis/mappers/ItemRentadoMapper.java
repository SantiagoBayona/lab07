package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.List;

import edu.eci.cvds.samples.entities.ItemRentado;
import org.apache.ibatis.annotations.Param;

public interface ItemRentadoMapper {

    public List<ItemRentado> consultarItems();

    public ItemRentado consultarItemRentado(@Param("idr") int id);
}
