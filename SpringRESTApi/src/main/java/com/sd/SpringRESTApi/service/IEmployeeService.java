package com.sd.SpringRESTApi.service;

import com.sd.SpringRESTApi.entity.Employee;
import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();
    Employee findById(int employeeId);
    Employee save(Employee employee);
    void deleteById(int employeeId);
}
