package co.example.dao;

import co.example.entity.Product;

import java.util.List;

public interface ProductDao {
    // CRUD Operations
    public void addProduct(Product product) throws DaoException;

    public void updateProduct(Product product) throws DaoException;

    public Product getProduct(Integer productId) throws DaoException;

    public void deleteProduct(Integer productId) throws DaoException;

    // Queries
    public List<Product> getAllProducts() throws DaoException;

    public List<Product> getProductByPriceRange(Double min, Double max) throws DaoException;

    public List<Product> getProductIncategory(Integer categoryId) throws DaoException;

    public List<Product> getProductNotInStock() throws DaoException;

    public List<Product> getProductOnorder() throws DaoException;

    public List<Product> getDiscontinuedProducts() throws DaoException;

    public long count() throws DaoException;
}
