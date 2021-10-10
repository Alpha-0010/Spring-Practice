package co.example.dao;

import co.example.entity.Product;

import java.util.List;

public class DummyProductDao implements ProductDao {
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
        return 0;
    }
}
