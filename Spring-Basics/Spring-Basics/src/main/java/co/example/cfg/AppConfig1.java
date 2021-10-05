package co.example.cfg;

import co.example.dao.DummyProductDao;
import co.example.dao.JdbcProductDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

// Spring as a factory of beans....
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig1 {

    @Value("${jdbc.driver}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.user}")
    private String user;
    @Value("${jdbc.password}")
    private String password;

    public AppConfig1(){
        System.out.println("Appconfig1 Instatiated...");
    }

    /*
    Lazy ensures that Spring container doesnot call the beans when the object of Spring container is created.
     */

    @Lazy
    @Bean
    public DummyProductDao dummyDao(){
        System.out.println("Appconfig1.dummydao() called");
        return new DummyProductDao();
    }

    // scope can be singleton or prototype since we are working on ApplicationContextConfig
    // But when we will work on spring mvc then we will also have request,session and global session.
    // If the scope is singleton then both the objects(dao & dao2) will point to the same jdbcDao.
    @Lazy
    @Scope("singleton")
    @Bean
    public JdbcProductDao jdbcDao(){
        System.out.println("Appconfig1.jdbcdao() called");
        JdbcProductDao dao = new JdbcProductDao();
        dao.setDriverClassName(driverClassName);
        dao.setUrl(url);
        dao.setUser(user);
        dao.setPassword(password);
        return dao;
    }

}
