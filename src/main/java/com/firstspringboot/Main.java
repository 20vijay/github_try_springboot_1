package com.firstspringboot;

import com.firstspringboot.autoconfig.School;
import com.firstspringboot.dao.College;
import com.firstspringboot.dao.MyBean;
import com.firstspringboot.jpatry.Employee;
import com.firstspringboot.jpatry.EmployeeDao;
import jakarta.transaction.Transactional;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;

//@SpringBootConfiguration  // making this a spring managed class
//@ComponentScan // this class config will be applicable for the classes inside this package
//@EnableAutoConfiguration  // enables configurations things like tomcat server
//instead of giving these above three you can just give below on

@SpringBootApplication
@PropertySource("classpath:custom folder/abc.properties")//our own property file to be taken by spring

public class Main implements CommandLineRunner {

    @Autowired
    WebApplicationContext context;
//    @Autowired
//    JdbcTemplate jdbcTemplate;


    public static void main(String[] args) {
      //  SpringApplication springApplication=new SpringApplication(Main.class,args);
//        springApplication.run();
        // as SpringApplication is a static method we can directly call the run of it.
//     SpringApplication.run(Main.class,args); // this is command line argument , for testing we changed the port of tomcat server to 8081 and it worked
System.out.println("spring boot begins");
        ConfigurableApplicationContext run = SpringApplication.run(Main.class,args);// u can add args like --server.port=8081 to run
        System.out.println("spring boot after run method");
//once the run method executes and spring boot starts whatevet class annotated with @component,Bean,Etc will be put inside the ioc containter, whenever we ask ioc will give the object of the class
       College collegeBean = run.getBean(College.class);

        System.out.println("Hello, World!"+collegeBean);// we set the name from the properties file using @Value we set to variable
        //application.properties or application.yml file will be automatically taken by spring boot, if u keep it inside a folder called "config" other names it wont work
        // when u export as a fat jar, the properties file will also be taken
        collegeBean.met();
        System.out.println("college name is "+collegeBean.getCollegeName());

        System.out.println("--------------------------------");
        System.out.println("begining of jpa");

        EmployeeDao employeeDao=run.getBean(EmployeeDao.class);
        System.out.println("--------------------------------"+employeeDao);
        Employee e1=run.getBean(Employee.class);



       // e1.setId(3);
        e1.setName("ajth");
        e1.setDepartment("computer");
        System.out.println("--------------------------------"+e1);
        employeeDao.save(e1);
        System.out.println("inserted");

    }

    @Override
    public void run(String... args) throws Exception {
System.out.println("hi this is the overridden method of command line runner");
        School school = context.getBean(School.class);
        System.out.println(school);

        Bike bike=context.getBean(Bike.class);
        System.out.println(bike);

        System.out.println("-----------------");

        MyBean myBean = context.getBean(MyBean.class);
        System.out.println(myBean);

        MyBean myBean1 = context.getBean(MyBean.class);
        System.out.println(myBean1);

        System.out.println("-----------------");

System.out.println("this is the last line i am adding from git");
System.out.println("this is another line added now");
        System.out.println("this time everything will look good yerrr");
    }
}
