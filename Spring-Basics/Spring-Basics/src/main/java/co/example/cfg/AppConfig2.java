package co.example.cfg;

import co.example.dao.JdbcProductDao;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig2 {

    @Value("${jdbc.driver}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.user}")
    private String user;
    @Value("${jdbc.password}")
    private String password;

    @Bean
    @SneakyThrows
    @Scope("prototype")
    public Connection connection() {
        Class.forName(driverClassName);
        return DriverManager.getConnection(url,user,password);
    }


    // Using connection() bean into jdbcDao bean...[Dependency Injection] --> Just pass the return type and name of the bean as its argument...
    @Bean
    @Scope("prototype")
    public JdbcProductDao jdbcDao(Connection connection){

//        JdbcProductDao dao = new JdbcProductDao();
//        dao.setConnection(connection); // Manual wiring...
//        return dao;
        return new JdbcProductDao();
    }

}
