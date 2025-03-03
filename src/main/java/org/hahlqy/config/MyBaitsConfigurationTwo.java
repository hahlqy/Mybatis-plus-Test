package org.hahlqy.config;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "org.hahlqy.dao.ds2",sqlSessionFactoryRef = "SqlSessionFactoryTwo")
public class MyBaitsConfigurationTwo {

    @Bean(name = "SqlSessionFactoryTwo")
    public SqlSessionFactory sqlSessionFactoryTwo(DataSource druidDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setDataSource(druidDataSource);
        sessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mybatis/ds2/*.xml"));
        sessionFactoryBean.setTypeAliasesPackage("org.hahlqy.vo");
        return  sessionFactoryBean.getObject();
    }
}
