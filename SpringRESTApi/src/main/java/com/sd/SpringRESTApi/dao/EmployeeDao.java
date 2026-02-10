package com.sd.SpringRESTApi.dao;

import com.sd.SpringRESTApi.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class EmployeeDao implements IEmployeeDao{
    private final EntityManager entityManager;

    @Autowired
    public EmployeeDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = this.entityManager.createQuery("select e from Employee e", Employee.class);
        List<Employee> result = query.getResultList();
        return result;
    }

    @Override
    public Employee findById(int employeeId) {
        return this.entityManager.find(Employee.class, employeeId);
    }

    @Override
    public Employee save(Employee employee) {
        //merge() => If primary_key(id) field value=0 then insert else update.
        Employee result = this.entityManager.merge(employee);
        return result;
    }

    @Override
    public void deleteById(int employeeId) {
        Employee employee = this.entityManager.find(Employee.class, employeeId);
        if(employee!=null){
            this.entityManager.remove(employee);
        }
    }
}
