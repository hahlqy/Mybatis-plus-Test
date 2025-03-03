package org.hahlqy.web;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.hahlqy.dao.ds1.TacoMapper;
import org.hahlqy.dao.ds2.UserMapper;
import org.hahlqy.vo.Taco;
import org.hahlqy.vo.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired
    private TacoMapper tacoMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/list")
    public List<Taco> list() {
        return tacoMapper.selecTacoList();
    }


    @GetMapping("/getOne/{id}")
    public Taco getOne(@PathVariable int id) {
        return tacoMapper.getTacoById(id);
    }

    @GetMapping("/getUserList")
    public List<User> getUserList() {
        return userMapper.selectAll();
    }

}
