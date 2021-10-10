package co.example.dao;

import co.example.entity.Product;
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
import java.util.List;

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
    public void addProduct(Product product) throws DaoException {

    }

    @Override
    public void updateProduct(Product product) throws DaoException {

    }

    @Override
    public Product getProduct(Integer productId) throws DaoException {
        return null;
    }

    @Override
    public void deleteProduct(Integer productId) throws DaoException {

    }

    @Override
    public List<Product> getAllProducts() throws DaoException {
        return null;
    }

    @Override
    public List<Product> getProductByPriceRange(Double min, Double max) throws DaoException {
        return null;
    }

    @Override
    public List<Product> getProductIncategory(Integer categoryId) throws DaoException {
        return null;
    }

    @Override
    public List<Product> getProductNotInStock() throws DaoException {
        return null;
    }

    @Override
    public List<Product> getProductOnorder() throws DaoException {
        return null;
    }

    @Override
    public List<Product> getDiscontinuedProducts() throws DaoException {
        return null;
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
