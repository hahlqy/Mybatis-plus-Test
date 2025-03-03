package org.hahlqy.web;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.hahlqy.dao.ds1.TacoMapper;
import org.hahlqy.dao.ds2.UserMapper;
import org.hahlqy.vo.Taco;
import org.hahlqy.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
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
        return tacoMapper.selectById(id);
    }


    @GetMapping("/page")
    public Page<Taco> page(int pageNo, int pageSize) {
        Page<Taco> page = new Page<>(pageNo, pageSize);
        return tacoMapper.selectPage(page, null);
    }

    @GetMapping("/getUserList")
    public List<User> getUserList() {
        return userMapper.selectAll();
    }

}
