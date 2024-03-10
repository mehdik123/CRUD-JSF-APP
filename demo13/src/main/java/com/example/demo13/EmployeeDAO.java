package com.example.demo13;

import java.util.List;

public interface EmployeeDAO {
     void addEmployee(Employee employee) ;
     void deleteEmployee(Employee employee);
     void updateEmployee(Employee employee) ;
     List<Employee> getAllEmployees();
}
