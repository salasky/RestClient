package org.Migranov.rest;

import org.Migranov.rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Component
public class Communication {
    public RestTemplate restTemplate;
    @Autowired
    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    private final String URL="http://localhost:8080/api/employees";

    public List<Employee> getAllEmployees(){
        ResponseEntity<List<Employee>> responseEntity=restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {});
        List<Employee> allemployees=responseEntity.getBody();
        return allemployees;
    }

    public Employee getEmployee(int id){
        Employee employee=restTemplate.getForObject(URL+"/"+id,Employee.class);
        return employee;
    }

    public void saveEmployee(Employee employee){
        int id=employee.getId();
        if(id==0){

            ResponseEntity<String> responseEntity=restTemplate.postForEntity(URL,employee,String.class);
            System.out.println("New employee was addet to DB");
            System.out.println(responseEntity.getBody());
        }
        else{
            restTemplate.put(URL,employee);
            System.out.println("Employee with id"+id+" was updated");
        }
    }

    public void deleteEmployee(int id){
        restTemplate.delete(URL+"/"+id);
        System.out.println("Employee with id "+id+"was deleted");

    }
}
