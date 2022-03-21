package org.Migranov.rest;

import org.Migranov.rest.configuration.MyConfig;
import org.Migranov.rest.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication=context.getBean("communication",Communication.class);
        List<Employee> allEmployees=communication.getAllEmployees();
        System.out.println(allEmployees);
        System.out.println("--------");
        Employee empId=communication.getEmployee(2);
        System.out.println(empId);
        System.out.println("--------");
        communication.deleteEmployee(12);
    }

}
