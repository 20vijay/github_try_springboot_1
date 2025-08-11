package com.firstspringboot.autoconfig;

import com.firstspringboot.Bike;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
//when the spring boot application starts, we put @SpringBootApplication anno, inside that we have @EnableAutoConfiguration anno with it
//so when we put that, all the required configurations(ex tomcat, dispatcherServlet etc) will be loaded into the libraries depends upon the mvn dependencies we have
// to check that go to External Libraries-->spring-boot-autoconfigure-->jar-->meta-inf-->spring-->autoconfigure.AutoConfiguration

//so above we have created our custom @AutoConfiguration file for School class, so we need to create our own autoconfiguration file
//for that inside resourcs create META-INF-->spring-->copy the spring autoconfigure name(org.springframework.boot.autoconfigure.AutoConfiguration.imports) and create a file with the name
//in the file put the school class like this com.firstspringboot.autoconfig.School, so here we are enabling autoconfiguration for School class

@ConditionalOnClass(name="com.firstspringboot.Bike")
//above line we added a conditional class which means only the AutoConfiguration will work and create a place in ioc when
// we have class called Bike, we no Bike class means the School class will not have a place in the IOC
public class School {

    @ConditionalOnBean(name="collegeBean")
// above line says only the below @Bean for creaing bike object will be executed when there is a bean named collegeBean
    //so on top of college class we put @Component("collegeBean"), so in the ioc the college obj will be there with a name collegeBean
    //so when the collegeBean exists, the belo bike obj creation bean will be executed by spring
    @Bean
    public Bike bikeObjCreation(){
        return new Bike();
    }
}
