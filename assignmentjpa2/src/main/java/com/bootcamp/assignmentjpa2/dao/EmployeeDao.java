package com.bootcamp.assignmentjpa2.dao;

import com.bootcamp.assignmentjpa2.entities.Employee;
//import com.bootcamp.assignmentjpa2.entities.EmployeeFirstNameAndLastName;
import com.bootcamp.assignmentjpa2.entities.EmployeeFirstNameAndLastName;
import com.bootcamp.assignmentjpa2.entities.EmployeeIDFirstNameAndAge;
import com.bootcamp.assignmentjpa2.repositories.EmployeeRepository;
import jdk.internal.agent.Agent;
import org.graalvm.compiler.nodes.calc.IntegerTestNode;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeDao {

    @Autowired
    EmployeeRepository employeeRepository;
    public void createEmployees(Employee employee){
        employeeRepository.save(employee);
    }

    public List<EmployeeFirstNameAndLastName> salaryGreaterThanAverageSalary(){
        List<EmployeeFirstNameAndLastName> employee=new ArrayList<>();

        Sort sort=Sort.by(Sort.Order.asc("age"),Sort.Order.desc("salary"));
        List<Object[]> employees=employeeRepository.salaryGreaterThanAverageSalary(sort);
        for (Object[] objects:employees) {
            employee.add(new EmployeeFirstNameAndLastName(objects[0].toString(),objects[1].toString()));
        }
        return employee;
    }



    public String updateAverageSalary(Integer salary){
        Integer avg=employeeRepository.averageSalary();
        employeeRepository.updateAverageSalary(salary,avg);
        return "Salary Updated Successfully";
    }

    public List<EmployeeIDFirstNameAndAge> lastNameSingh(){
        List<EmployeeIDFirstNameAndAge> employeeIDFirstNameAndAges=new ArrayList<>();
        List<Object[]> employeeList=employeeRepository.lnameSingh();
        for (Object[] objects:employeeList) {
            employeeIDFirstNameAndAges.add(new EmployeeIDFirstNameAndAge((Integer)objects[0],objects[1].toString(),(Integer)objects[2]));
        }
        return employeeIDFirstNameAndAges;
    }

    public void deleteBySalary(){
        Integer minSalary=employeeRepository.minSalary();
        employeeRepository.deleteBySalary(minSalary);
    }

    public void deleteEmployee(Integer age) {
        employeeRepository.delete(age);
    }
}
