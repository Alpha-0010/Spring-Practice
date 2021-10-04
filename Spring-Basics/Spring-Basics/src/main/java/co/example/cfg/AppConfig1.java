package co.example.cfg;

import co.example.dao.DummyProductDao;
import co.example.dao.JdbcProductDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Spring as a factory of beans....
@Configuration
public class AppConfig1 {

    public AppConfig1(){
        System.out.println("Appconfig1 Instatiated...");
    }

    @Bean
    public DummyProductDao dummyDao(){
        System.out.println("Appconfig1.dummydao() called");
        return new DummyProductDao();
    }

    @Bean
    public JdbcProductDao jdbcDao(){
        System.out.println("Appconfig1.jdbcdao() called");
        return new JdbcProductDao();
    }

}
