package com.bootcamp.assignmentjpa2.controller;

import com.bootcamp.assignmentjpa2.dao.EmployeeDao;
import com.bootcamp.assignmentjpa2.dto.EmployeeFirstNameAndLastName;
import com.bootcamp.assignmentjpa2.dto.EmployeeIDFirstNameAndAge;
import com.bootcamp.assignmentjpa2.entities.*;
import com.bootcamp.assignmentjpa2.entities.componentMapping.Employeemapping;
import com.bootcamp.assignmentjpa2.entities.componentMapping.Salarymapping;
import com.bootcamp.assignmentjpa2.entities.joined.CricketPlayerjoined;
import com.bootcamp.assignmentjpa2.entities.joined.FootballPlayerjoined;
import com.bootcamp.assignmentjpa2.entities.singleTable.Cricketplayersingle;
import com.bootcamp.assignmentjpa2.entities.singleTable.Footballplayersingle;
import com.bootcamp.assignmentjpa2.entities.tablePerClass.PlayerTableCricket;
import com.bootcamp.assignmentjpa2.entities.tablePerClass.PlayerTableFootball;
import com.bootcamp.assignmentjpa2.repositories.EmployeeMappingRepository;
import com.bootcamp.assignmentjpa2.repositories.JoinedPlayerRepository;
import com.bootcamp.assignmentjpa2.repositories.SinglePlayerRepository;
import com.bootcamp.assignmentjpa2.repositories.TablePlayerRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    SinglePlayerRepository singlePlayerRepository;
    @Autowired
    TablePlayerRepositry tablePlayerRepositry;
    @Autowired
    JoinedPlayerRepository joinedPlayerRepository;
    @Autowired
    EmployeeMappingRepository employeeMappingRepository;




    @PostMapping(path="/employees")
    public void createEmployees(@RequestBody Employee employee){
        employeeDao.createEmployees(employee);
    }

//    Display the first name, last name of all employees having
//    salary greater than average salary ordered in ascending by their age and in descending by their salary.
    @GetMapping(path="/employees/salary/aboveaverage")
    public List<EmployeeFirstNameAndLastName> findEmployeeGreaterThanAverageSalary(){
        return employeeDao.salaryGreaterThanAverageSalary();
    }

//    Update salary of all employees by a salary passed as a parameter
//    whose existing salary is less than the average salary.
    @PostMapping(path="/employees/update/salary")
    public String updateSalary(@RequestParam("salary") Integer salary){
        String str=employeeDao.updateAverageSalary(salary);
        return str;
    }

//Delete all employees with minimum salary.
    @GetMapping(path="/employees/salary/min")
    public void deleteBySalaryMin(){
        employeeDao.deleteBySalary();
    }




//    Display the id, first name, age of all employees where last name ends with "singh"
    @GetMapping(path ="/employees/singh")
    public List<EmployeeIDFirstNameAndAge> lastNameSingh(){
        return employeeDao.lastNameSingh();
    }



//    Delete all employees with age greater than 45(Should be passed as a parameter)
    @DeleteMapping(path = "/employees/delete/agegreater")
    public void deleteEmployee(@RequestParam("Age")Integer age){
        employeeDao.deleteEmployee(age);
    }


//    ----------------Inheritance Mapping:--------------

//    Implement and demonstrate Single Table strategy.
    @GetMapping(path="/players/single")
    public void insertPlayer(){
        Footballplayersingle footballPlayer=new Footballplayersingle();
        footballPlayer.setId(1);
        footballPlayer.setName("Messi");
        footballPlayer.setGoal(55);
        singlePlayerRepository.save(footballPlayer);

        Cricketplayersingle cricketPlayer=new Cricketplayersingle();
        cricketPlayer.setId(2);
        cricketPlayer.setName("Virat");
        cricketPlayer.setRun(745);
        singlePlayerRepository.save(cricketPlayer);

    }

    //    Implement and demonstrate Joined strategy.
    @GetMapping(path="/players/joined/football")
    public void playerFootball() {
        FootballPlayerjoined playerFootball = new FootballPlayerjoined();
        playerFootball.setId(1);
        playerFootball.setName("Neymar");
        playerFootball.setGoal(55);
        joinedPlayerRepository.save(playerFootball);
    }
    @GetMapping(path="/players/joined/cricket")
    public void playerCricket() {
        CricketPlayerjoined cricketPlayer=new CricketPlayerjoined();
        cricketPlayer.setId(2);
        cricketPlayer.setName("Sachin");
        cricketPlayer.setRun(745);
        joinedPlayerRepository.save(cricketPlayer);
    }


//    Implement and demonstrate Table Per Class strategy.
    @GetMapping(path="/players/table/football")
    public void insertPlayerFootball() {
        PlayerTableFootball playerFootball = new PlayerTableFootball();
        playerFootball.setId(1);
        playerFootball.setName("Neymar");
        playerFootball.setGoal(55);
        tablePlayerRepositry.save(playerFootball);
    }
    @GetMapping(path="/players/table/cricket")
    public void insertPlayerCricket() {
        PlayerTableCricket cricketPlayer=new PlayerTableCricket();
        cricketPlayer.setId(1);
        cricketPlayer.setName("Sachin");
        cricketPlayer.setRun(745);
        tablePlayerRepositry.save(cricketPlayer);
    }



//    Component Mapping:
//
//    Implement and demonstrate Embedded mapping using employee table having following fields:
//    id, firstName, lastName, age, basicSalary, bonusSalary, taxAmount, specialAllowanceSalary.
    @GetMapping(path="/employees/mapping")
    public void componentMapping(){
        Employeemapping employeeMapping=new Employeemapping();
        employeeMapping.setId(1);
        employeeMapping.setFirstname("Mike");
        employeeMapping.setLastname("Potter");
        employeeMapping.setAge(25);
        Salarymapping salaryMapping=new Salarymapping();
        salaryMapping.setBasicsalary(35000);
        salaryMapping.setBonussalary(10000);
        salaryMapping.setTaxamount(500);
        salaryMapping.setSpecialallowancesalary(1500);
        employeeMapping.setSalaryMapping(salaryMapping);
        employeeMappingRepository.save(employeeMapping);
    }

}
