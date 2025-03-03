package org.hahlqy.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "org.hahlqy.dao.ds1",sqlSessionFactoryRef = "SqlSessionFactoryOne")
public class MybatisConfigurationOne {


    @Bean
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("Aa123456");
        return druidDataSource;
    }


    @Bean(name = "SqlSessionFactoryOne")
    @Primary
    public SqlSessionFactory sqlSessionFactoryOne( DataSource druidDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setDataSource(druidDataSource);
        sessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mybatis/ds1/*.xml"));
        sessionFactoryBean.setTypeAliasesPackage("org.hahlqy.vo");
        return  sessionFactoryBean.getObject();
    }

}
