package co.example.cfg;

import co.example.dao.JdbcProductDao;
import lombok.SneakyThrows;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@ComponentScan(basePackages = {"co.example.dao"})
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig3 {

    @Value("${jdbc.driver}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.user}")
    private String user;
    @Value("${jdbc.password}")
    private String password;

    @Bean
    public DataSource dataSource(){
        // Connection Pool...
        BasicDataSource bds=new BasicDataSource();
        bds.setDriverClassName(driverClassName);
        bds.setUrl(url);
        bds.setUsername(user);
        bds.setPassword(password);

        // Initial 10 connections available...
        bds.setInitialSize(10);
        bds.setMaxTotal(100);
        bds.setMaxWaitMillis(500);
        bds.setMaxIdle(50);
        bds.setMinIdle(2);

        return bds;
    }

//    We don't need this instead we can use component and componentScan annotations in jdbcProductDao and AppConfig3 respectively...
//    @Bean
//    public JdbcProductDao jdbcDao(){
//        return new JdbcProductDao();
//    }

}

