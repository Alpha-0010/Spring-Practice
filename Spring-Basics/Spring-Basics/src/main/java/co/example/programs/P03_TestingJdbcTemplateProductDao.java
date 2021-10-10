package co.example.programs;

import co.example.cfg.AppConfig4;
import co.example.dao.DaoException;
import co.example.dao.ProductDao;
import co.example.entity.Product;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class P03_TestingJdbcTemplateProductDao {
    public static void main(String[] args) throws DaoException {
        AnnotationConfigApplicationContext ctx;
        ctx=new AnnotationConfigApplicationContext(AppConfig4.class);

        ProductDao dao = ctx.getBean("jtDao", ProductDao.class);

        Product p=dao.getProduct(1);
        System.out.println(p);

        p.setUnitPrice(p.getUnitPrice()+1);
        dao.updateProduct(p);
        System.out.println("Price Updated");

        List<Product> list = dao.getProductByPriceRange(50.0, 200.0);

        System.out.println("There are "+list.size()+" products between 50.0 and 200.0");

        list=dao.getDiscontinuedProducts();

        System.out.println("There are "+ list.size()+" discontinued products");

        ctx.close();
    }
}
