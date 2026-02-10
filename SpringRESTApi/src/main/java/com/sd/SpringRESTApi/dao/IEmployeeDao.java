package com.sd.SpringRESTApi.dao;

import com.sd.SpringRESTApi.entity.Employee;

import java.util.List;

public interface IEmployeeDao {
    List<Employee> findAll();
    Employee findById(int employeeId);
    Employee save(Employee employee);
    void deleteById(int employeeId);
}
