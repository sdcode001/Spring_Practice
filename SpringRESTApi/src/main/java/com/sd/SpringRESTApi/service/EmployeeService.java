package com.sd.SpringRESTApi.service;

import com.sd.SpringRESTApi.dao.IEmployeeDao;
import com.sd.SpringRESTApi.entity.Employee;
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

    //Best Practice- Use @Transactional annotation at service layer method instead on dao layer methods.
    @Override
    @Transactional
    public Employee save(Employee employee) {
        return this.employeeDao.save(employee);
    }

    //Best Practice- Use @Transactional annotation at service layer method instead on dao layer methods.
    @Override
    @Transactional
    public void deleteById(int employeeId) {
       this.employeeDao.deleteById(employeeId);
    }
}
