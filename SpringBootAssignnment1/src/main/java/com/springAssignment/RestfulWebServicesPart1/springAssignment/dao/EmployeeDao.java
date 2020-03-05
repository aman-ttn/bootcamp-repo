package com.springAssignment.RestfulWebServicesPart1.springAssignment.dao;

import com.springAssignment.RestfulWebServicesPart1.springAssignment.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class EmployeeDao {
    List<Employee> employeeList=new ArrayList<Employee>();
    private static Integer countEmployee=0;
    public Employee addEmp(Employee employee){
        countEmployee++;
        if(employee.getId()==null){
            employee.setId(countEmployee);
        }
        employeeList.add(employee);
        return employee;
    }

    public List<Employee> getEmp(){
        return employeeList;
    }

    public Employee getOneEmp(int id){
        for(Employee emp:employeeList){
            if(emp.getId()==id){
                return emp;
            }
        }
        return null;
    }



    public boolean deleteEmp(Integer id){
        boolean status=false;
        Iterator<Employee> iterator=employeeList.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getId()==id){
                iterator.remove();
                status=true;
            }
        }
        return status;
    }

    public void putEmpl(Integer id,Employee employee){
        Iterator<Employee> iterator=employeeList.iterator();
        while(iterator.hasNext()){
            Employee emp=iterator.next();
            if(emp.getId()==id){
                emp.setName(employee.getName());
                emp.setAge(employee.getAge());
            }
        }
    }
}
