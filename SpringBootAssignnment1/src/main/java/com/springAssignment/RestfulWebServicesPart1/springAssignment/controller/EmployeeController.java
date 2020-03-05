package com.springAssignment.RestfulWebServicesPart1.springAssignment.controller;

import com.springAssignment.RestfulWebServicesPart1.springAssignment.dao.EmployeeDao;
import com.springAssignment.RestfulWebServicesPart1.springAssignment.entity.Employee;
import com.springAssignment.RestfulWebServicesPart1.springAssignment.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @PostMapping(path="/addEmployees")
    public ResponseEntity<Object> addEmployee(@Valid @RequestBody Employee employee){
        Employee current_employee=employeeDao.addEmp(employee);
        URI location=ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(current_employee.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @GetMapping(path="/getEmployees")
    public List<Employee> getEmployee(){
        return employeeDao.getEmp();
    }

    @GetMapping(path="/getEmployees/{id}")
    public Employee getOneEmployee(@PathVariable Integer id){
        Employee OneEmployee=employeeDao.getOneEmp(id);
        if(OneEmployee==null){
            throw new EmployeeNotFoundException("id : "+id);
        }
        return OneEmployee;
    }

    @DeleteMapping(path="/deleteEmployees/{id}")
    public void deleteEmployee(@PathVariable Integer id){

        boolean status=employeeDao.deleteEmp(id);
        if(!status){
            throw new EmployeeNotFoundException("Record Not Found");
        }

    }

    @PutMapping(path="putEmployee/{id}")
    public void putEmployee(@PathVariable Integer id,@Valid @RequestBody Employee employee){
            employeeDao.putEmpl(id,employee);
    }
}
