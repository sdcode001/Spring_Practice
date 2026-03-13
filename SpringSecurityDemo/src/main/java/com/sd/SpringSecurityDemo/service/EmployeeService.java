package com.sd.SpringSecurityDemo.service;

import com.sd.SpringSecurityDemo.dao.IEmployeeDao;
import com.sd.SpringSecurityDemo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class EmployeeService implements IEmployeeService{
    private final IEmployeeDao employeeDao;

    @Autowired
    public EmployeeService(IEmployeeDao employeeDao){
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAll() {
        return this.employeeDao.findAll();
    }

    @Override
    public Employee findById(int employeeId) {
        return this.employeeDao.findById(employeeId);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return this.employeeDao.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(int employeeId) {
        this.employeeDao.deleteById(employeeId);
    }
}
