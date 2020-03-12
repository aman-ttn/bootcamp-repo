package com.springjpa.assignmentjpa;

import com.springjpa.assignmentjpa.entities.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.springjpa.assignmentjpa.repositories.EmployeeRepository;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AssignmentjpaApplicationTests {
//
//	@Autowired
//	EmployeeRepository employeeRepository;


	@Test
	void contextLoads() {
	}

//(3) Perform Create Operation on Entity using Spring Data JPA
//	@Test
//	void employeeCreate(){
//		Employee employee=new Employee();
//		employee.setId(3);
//		employee.setAge(29);
//		employee.setName("Ajay");
//		employee.setLocation("Noida");
//
//		employeeRepository.save(employee);
//	}

//	(4) Perform Update Operation on Entity using Spring Data JPA
//	@Test
//	void employeeUpdate(){
//		Employee employee=employeeRepository.findById(1).get();
//		employee.setAge(20);
//		employee.setName("Anuj");
//		employeeRepository.save(employee);
//		}

//		(5) Perform Delete Operation on Entity using Spring Data JPA
//		@Test
//	void employeeDelete(){
//			employeeRepository.deleteById(1);
//		}

//		(5) Perform Read Operation on Entity using Spring Data JPA
//	@Test
//	void employeeRead(){
//		employeeRepository.findAll().forEach(p-> System.out.println(p.getName()+" "+p.getId()+" "+p.getAge()+" "+p.getLocation()));
//	}

//	(6) Get the total count of the number of Employees
//	@Test
//	void employeeCount(){
//		System.out.println(employeeRepository.count());
//	}

//	(7) Implement Pagination and Sorting on the bases of Employee Age
//	@Test
//	void employeeFindAllPagingAndSorting(){
//		Pageable pageable = PageRequest.of(0,2, Sort.by(Sort.Direction.DESC,"age"));
//		employeeRepository.findAll(pageable).forEach(e-> System.out.println(e.getName()));
//
//	}

//	(8) Create and use finder to find Employee by Name
//	@Test
//	void findByName(){
//		List<Employee> employees= employeeRepository.findByName("Aradhya");
//		if(employees!=null) {
//			employees.forEach(e -> System.out.println(e.getName()));
//		}
//	}

//	@Test
//	void findByNameLike(){
//		List<Employee> employees=employeeRepository.findByNameLike("A%");
//		if(employees!=null) {
//			employees.forEach(e -> System.out.println("The employee whoose name started with character A : "+e.getName()));
//		}
//	}

//	@Test
//	void findByAgeBetween(){
//		List<Employee> employees=employeeRepository.findByAgeBetween(28,32);
//		if(employees!=null) {
//			employees.forEach(e -> System.out.println("The employee having age between 28 and 32 : "+e.getName()));
//		}
//
//	}


}


