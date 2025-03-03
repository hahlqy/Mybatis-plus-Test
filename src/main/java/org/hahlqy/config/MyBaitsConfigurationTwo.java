package org.hahlqy.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"org.hahlqy.dao.ds2"},sqlSessionFactoryRef = "SqlSessionFactoryTwo")
public class MyBaitsConfigurationTwo {

    @Bean(name = "SqlSessionFactoryTwo")
    public SqlSessionFactory sqlSessionFactoryTwo(DataSource druidDataSource, MybatisPlusInterceptor interceptor) throws Exception {
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        mybatisPlus.setDataSource(druidDataSource);
        mybatisPlus.setMapperLocations(resolver.getResources("classpath*:mybatis/ds2/*.xml"));
        mybatisPlus.setTypeAliasesPackage("org.hahlqy.vo");
        mybatisPlus.setPlugins(interceptor);
        return  mybatisPlus.getObject();
    }
}
