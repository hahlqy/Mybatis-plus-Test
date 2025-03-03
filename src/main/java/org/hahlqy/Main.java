package org.hahlqy;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.hahlqy.dao.ds1.TacoMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        MybatisXML();
//        MybatisConfig();
    }


    public static void MybatisConfig(){
        System.out.println("Mybatis Configuration");
        Configuration configuration = getConfiguration();
        configuration.addMapper(TacoMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        try(SqlSession session = sqlSessionFactory.openSession()) {
            TacoMapper mapper = session.getMapper(TacoMapper.class);
            System.out.println(mapper.getTacoById(1));
        }
    }

    private static Configuration getConfiguration() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("Aa123456");
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, druidDataSource);
        return new Configuration(environment);
    }

    public static void  MybatisXML() throws IOException {
        String resource = "mybatis-config.xml";
        String configResource = "config.properties";
        try(InputStream inputStream = Resources.getResourceAsStream(resource);
            InputStream config = Resources.getResourceAsStream(configResource)){
            Properties properties = new Properties();
            properties.load(config);
            properties.setProperty("username", "root");
            properties.setProperty("password", "Aa123456");
            /**
             * Mybatis 记载配置顺序，build > XML中的properties > 配置文件中的resource文件中的配置项
             * 相同名字的数据项会被覆盖
             */
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"uat", properties);
            try(SqlSession session = sqlSessionFactory.openSession()) {
                TacoMapper mapper = session.getMapper(TacoMapper.class);
                System.out.println(mapper.getTacoById(1));
                System.out.println(mapper.getTacoById(1));
                System.out.println(mapper.getTacoById(1));
            }
        }
    }
}