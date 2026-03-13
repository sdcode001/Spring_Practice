package com.sd.SpringSecurityDemo.service;

import com.sd.SpringSecurityDemo.entity.Employee;
import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();
    Employee findById(int employeeId);
    Employee save(Employee employee);
    void deleteById(int employeeId);
}
