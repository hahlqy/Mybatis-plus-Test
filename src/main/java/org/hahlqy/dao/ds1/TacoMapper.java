package org.hahlqy.dao.ds1;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.hahlqy.vo.Taco;
import java.util.List;

@Mapper
public interface TacoMapper  {

    List<Taco> selecTacoList();

    @Select("select * from taco where id =#{id}")
    Taco getTacoById(@Param("id") int id);
}
