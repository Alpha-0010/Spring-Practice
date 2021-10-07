package co.example.programs;

import co.example.cfg.AppConfig4;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class P02_TestingJdbcTemplate {

    static JdbcTemplate template;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx;
        ctx=new AnnotationConfigApplicationContext(AppConfig4.class);
        template=ctx.getBean(JdbcTemplate.class);

        //insertShipper();
        //updateShipper(4,"12345678");
        //printProductCount();
        //printShipperName(4);
        //printProductDetails(33);
        //printAllShippers();
        //printAllShipperNames();

        ctx.close();
    }

    static void printAllShipperNames() {
        String sql="select company_name from shippers";
        List<String> list = template.queryForList(sql, String.class);
        for (String name : list){
            System.out.println(name);
        }
    }

    static void printAllShippers() {
        String sql="select * from shippers";
        List<Map<String, Object>> list = template.queryForList(sql);
        for (Map<String,Object> data : list){
            System.out.println(data.get("company_name")+" --> "+data.get("phone"));
        }
    }

    static void printProductDetails(int productId) {
        String sql="select * from products where product_id=?";
        Map<String,Object> data=template.queryForMap(sql,productId);
         //System.out.println(data);
         for (String key : data.keySet()){
             System.out.println(key+"-->"+data.get(key));
         }
    }

    static void printShipperName(int id) {
        String sql="select company_name from shippers where shipper_id=?";
        String name=template.queryForObject(sql,String.class,id);
        System.out.println("Shipper name= "+name);
    }

    static void printProductCount() {
        String sql="select count(*) from products";
        Integer pc=template.queryForObject(sql,Integer.class);
        System.out.println("There are "+pc+" products.");
    }

    static void updateShipper(int id, String phone) {
        String sql="update shippers set phone=? where shipper_id=?";
        template.update(sql,phone,id);
         System.out.println("Phone Updated");
    }

    static void insertShipper() {
        template.update("insert into shippers values(?,?,?)",4,"Bob","9080437002");
        System.out.println("New Shipper data inserted!");
    }
}
