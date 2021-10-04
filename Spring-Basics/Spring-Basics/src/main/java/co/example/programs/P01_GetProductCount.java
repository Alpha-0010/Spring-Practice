package co.example.programs;

import co.example.cfg.AppConfig1;
import co.example.dao.ProductDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class P01_GetProductCount {
    public static void main(String[] args) {
        // Our Dependency....
        ProductDao dao;

        // Variable representing Spring container....
        AnnotationConfigApplicationContext ctx;

        // Object of Spring container....
        // Spring Container is going to read the beans defined in this AppConfig1 class which are DummyProductDao and JdbcProductDao...
        ctx=new AnnotationConfigApplicationContext(AppConfig1.class);

        System.out.println("-------------------------");

        dao=ctx.getBean("dummyDao",ProductDao.class);
        System.out.println("doa is an instanceof "+dao.getClass().getName());
        System.out.println("There are "+dao.count()+" products.");

        ctx.close();
    }
}
