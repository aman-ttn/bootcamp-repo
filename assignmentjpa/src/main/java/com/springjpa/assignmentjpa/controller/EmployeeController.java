package com.springjpa.assignmentjpa.controller;

import com.springjpa.assignmentjpa.entities.Employee;
import com.springjpa.assignmentjpa.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;


//(3) Perform Create Operation on Entity using Spring Data JPA
    @PostMapping(path="/employees" )
    public void employeeCreate(@RequestBody Employee employee){
        employeeRepository.save(employee);
    }

//    (4) Perform Update Operation on Entity using Spring Data JPA
    @PutMapping(path="/employees/{id}")
    public List<Employee> employeeUpdate(@PathVariable Integer id,@RequestBody Employee employee){
        Employee employees=employeeRepository.findById(id).get();
        employees.setAge(employee.getAge());
        employees.setName(employee.getName());
        employees.setLocation(employee.getLocation());
        employeeRepository.save(employees);
        return (List<Employee>) employeeRepository.findAll();

    }


//(5) Perform Delete Operation on Entity using Spring Data JPA
    @DeleteMapping(path="/employees/{id}")
    void employeeDelete(@PathVariable Integer id){
        employeeRepository.deleteById(id);
    }

//(5) Perform Read Operation on Entity using Spring Data JPA
    @GetMapping(path="/employees")
    Iterable<Employee> employeeRead(){
        return employeeRepository.findAll();
    }

//(6) Get the total count of the number of Employees
    @GetMapping(path="/employees/count")
    public Long employeeCount(){
        return employeeRepository.count();
    }

//(7) Implement Pagination and Sorting on the bases of Employee Age
    @GetMapping(path="/employees/pagingandsorting")
    List<Employee> employeeFindAllPagingAndSorting(){
        Pageable pageable = PageRequest.of(0,2, Sort.by(Sort.Direction.DESC,"age"));
        List<Employee> employees=new ArrayList<>();
        employeeRepository.findAll(pageable).forEach(e-> employees.add(e));
        return employees;
    }

//(8) Create and use finder to find Employee by Name
    @GetMapping(path="/employees/{name}")
    public List<Employee> findByName(@PathVariable String name){
        List<Employee> employees= employeeRepository.findByName(name);
      return employees;
    }

//(9) Create and use finder to find Employees starting with A character
    @GetMapping(path="/employees/like")
    List<Employee> findByNameLike(){
        List<Employee> employees=employeeRepository.findByNameLike("A%");
        if(employees!=null) {
           return employees;
        }
        return employees;
    }

//(10) Create and use finder to find Employees Between the age of 28 to 32
    @GetMapping(path="/employees/agebetween")
    List<Employee> findByAgeBetween(){
        List<Employee> employees=employeeRepository.findByAgeBetween(28,32);
        if(employees!=null) {
            employees.forEach(e -> System.out.println("The employee having age between 28 and 32 : "+e.getName()));
        }
        return employees;

    }



}
