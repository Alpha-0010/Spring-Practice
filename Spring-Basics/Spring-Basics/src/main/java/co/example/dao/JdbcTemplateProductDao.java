package co.example.dao;

import co.example.entity.Category;
import co.example.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component("jtDao")
public class JdbcTemplateProductDao implements ProductDao {

    @Autowired(required = false)
    private JdbcTemplate template;

    static RowMapper<Product> productRowMapper=new RowMapper<Product>() {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product p=new Product();
            p.setProductId(rs.getInt("product_id"));
            p.setProductName(rs.getString("product_name"));
            p.setSupplierId(rs.getInt("supplier_id"));
            p.setCategoryId(rs.getInt("category_id"));
            p.setQuantityPerUnit(rs.getString("quantity_per_unit"));
            p.setUnitPrice(rs.getDouble("unit_price"));
            p.setUnitsInStock(rs.getInt("units_in_stock"));
            p.setUnitsOnOrder(rs.getInt("units_on_order"));
            p.setReorderLevel(rs.getInt("reorder_level"));
            p.setDiscontinued(rs.getInt("discontinued"));

            return p;
        }
    };

    @Override
    public void addProduct(Product p) throws DaoException {
        String sql="Insert into products values(?,?,?,?,?,?,?,?,?,?)";
        template.update(sql,p.getProductId(),p.getProductName(),p.getSupplierId(),p.getCategoryId(),p.getQuantityPerUnit(),p.getUnitPrice(),p.getUnitsInStock(),p.getUnitsOnOrder(),p.getReorderLevel(),p.getDiscontinued());
    }

    @Override
    public void updateProduct(Product p) throws DaoException {
        String sql="Update products set product_name=?,supplier_id=?,category_id=?,quantity_per_unit=?,unit_price=?,units_in_stock=?,units_on_order=?,reorder_level=?,discontinued=? where product_id=?";
        template.update(sql,p.getProductName(),p.getSupplierId(),p.getCategoryId(),p.getQuantityPerUnit(),p.getUnitPrice(),p.getUnitsInStock(),p.getUnitsOnOrder(),p.getReorderLevel(),p.getDiscontinued(),p.getProductId());
    }

    @Override
    public Product getProduct(Integer productId) throws DaoException {
        String sql="select * from products where product_id=?";
        return template.queryForObject(sql,productRowMapper,productId);
    }

    @Override
    public void deleteProduct(Integer productId) throws DaoException {
        // Soft Delete...
        String sql="update products set discontinued=1 where product_id=?";
        template.update(sql,productId);
    }

    @Override
    public List<Product> getAllProducts() throws DaoException {
        String sql="select * from products";
        return template.query(sql,productRowMapper);
    }

    @Override
    public List<Product> getProductByPriceRange(Double min, Double max) throws DaoException {
        String sql="select * from products where unit_price between ? and ?";
        return template.query(sql,productRowMapper,min,max);
    }

    @Override
    public List<Product> getProductIncategory(Integer categoryId) throws DaoException {
        String sql="select * from products where category_id=?";
        return template.query(sql,productRowMapper,categoryId);
    }

    @Override
    public List<Product> getProductNotInStock() throws DaoException {
        String sql="select * from products where units_in_stock=0";
        return template.query(sql,productRowMapper);
    }

    @Override
    public List<Product> getProductOnorder() throws DaoException {
        String sql="select * from products where units_on_order>0";
        return template.query(sql,productRowMapper);
    }

    @Override
    public List<Product> getDiscontinuedProducts() throws DaoException {
        String sql="select * from products where discontinued=1";
        return template.query(sql,productRowMapper);
    }

    @Override
    public long count() throws DaoException {
        String sql="select count(*) from products";
        return template.queryForObject(sql,long.class);
    }
}
