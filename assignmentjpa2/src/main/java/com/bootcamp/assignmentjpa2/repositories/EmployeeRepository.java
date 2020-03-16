package com.bootcamp.assignmentjpa2.repositories;

import com.bootcamp.assignmentjpa2.entities.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee,Integer>, PagingAndSortingRepository<Employee,Integer> {
    @Query("select firstName,lastName from Employee emp where salary > (select AVG(salary) from emp)")
    public List<Object[]> salaryGreaterThanAverageSalary(Sort sort);


//    @Query("update emp set emp.salary=:Salary from Employee emp where emp.salary < (select AVG(emp.salary) from emp)")
    @Query("select avg(salary) from Employee emp")
    public Integer averageSalary();

    @Modifying
    @Transactional
    @Query("update Employee set salary=:salary  where salary < :avg")
    public void updateAverageSalary(@Param("salary") Integer salary, @Param("avg") Integer avg);


    @Query("select min(salary) from Employee emp")
    public Integer minSalary();

    @Modifying
    @Transactional
    @Query("delete from Employee where salary=:min")
    public void deleteBySalary(@Param("min") Integer min);

    @Query(value = "select empid,empfirstname,empAge from employeetable emp where emplastname='singh'",nativeQuery = true)
    public List<Object[]> lnameSingh();





    @Modifying
    @Transactional
    @Query(value ="delete from employeetable where empage > :Age",nativeQuery = true)
    public void delete(@Param("Age") Integer Age);


}
