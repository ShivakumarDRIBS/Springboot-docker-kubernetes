package com.example.demo.controller;

import com.example.demo.model.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/employee")
public class DemoController {

    private static List<Employee> employeeList = new ArrayList<>();

    public static final Logger log = Logger.getLogger(DemoController.class.getName());

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct: loading data...");
        Employee empHarshi = Employee.builder().empId(1).empname("Harshi").empLocation("Garani").build();
        Employee empShiva = Employee.builder().empId(2).empname("Shiva").empLocation("Sira").build();
        Employee empShrithvik = Employee.builder().empId(3).empname("Shrithvik").empLocation("Tumkur").build();
        Employee empRam = Employee.builder().empId(4).empname("Ram").empLocation("Madhugiri").build();
        employeeList.add(empHarshi);
        employeeList.add(empShiva);
        employeeList.add(empShrithvik);
        employeeList.add(empRam);
    }


    @GetMapping("/v1/getEmployeeById/{id}")
    public ResponseEntity getEmployeeById(@PathVariable int id){
        Employee matchingEmployee = employeeList.stream().filter(emp-> emp.getEmpId()==id).findFirst().get();
        return ResponseEntity.ok(matchingEmployee);
    }

    @GetMapping("/v1/getEmployee")
    public ResponseEntity getEmployees(){
        log.info("getEmployees: getting employees...");
        return ResponseEntity.ok(employeeList);
    }

    @PostMapping("v1/addEmployee")
    public ResponseEntity addEmployee(@RequestBody Employee employee){
        employeeList.add(employee);
        return ResponseEntity.ok(employeeList);
    }

    @PutMapping("v1/putEmployee/{id}")
    public ResponseEntity putEmployee(@RequestBody Employee employee, @PathVariable int id){
        Employee employeeFound;
        try{
            employeeFound = employeeList.stream().filter(emp -> emp.getEmpId()==id).findFirst().get();
        }catch (Exception exc){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Employee found");
        }
        employeeFound.setEmpname(employee.getEmpname());
        employeeFound.setEmpLocation(employee.getEmpLocation());
        return ResponseEntity.ok(employeeList);
    }
}
