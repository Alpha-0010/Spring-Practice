package co.example.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component("jdbcDao")
@NoArgsConstructor
@Getter
@Setter
public class JdbcProductDao implements ProductDao{

    private String driverClassName;
    private String url;
    private String user;
    private String password;

    @Autowired(required = false) // Autowiring if we donot wish to use manualWiring in Appconfig2.java...
    private Connection connection;

    @Autowired(required = false)
    private DataSource dataSource;

    @SneakyThrows
    private Connection createConnection() {
        // for AppConfig3
        if(dataSource!=null){
            return dataSource.getConnection();
        }
        // for AppConfig2
        if(connection!=null && connection.isClosed()==false){
            return connection;
        }
        // for AppConfig1
        Class.forName(driverClassName);
        return DriverManager.getConnection(url,user,password);
    }

    @Override
    public long count() {
        try(
                Connection conn=createConnection();
                PreparedStatement stmt=conn.prepareStatement("SELECT COUNT(*) FROM PRODUCTS");
                ResultSet rs= stmt.executeQuery();
                ){
            rs.next();
            return rs.getLong(1); // 1 corresponds to first and only column of the sql statement...
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }

    }
}
