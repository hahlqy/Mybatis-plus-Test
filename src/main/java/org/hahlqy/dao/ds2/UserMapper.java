package org.hahlqy.dao.ds2;

import org.apache.ibatis.annotations.Mapper;
import org.hahlqy.vo.User;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selectAll();

}
