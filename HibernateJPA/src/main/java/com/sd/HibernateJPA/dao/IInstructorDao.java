package com.sd.HibernateJPA.dao;

import com.sd.HibernateJPA.entity.Instructor;
import com.sd.HibernateJPA.entity.InstructorDetails;

public interface IInstructorDao {
    void save(Instructor instructor);
    Instructor findById(Integer id);
    void deleteById(Integer id);
    InstructorDetails findInstructorDetailsById(Integer instructorDetailsId);
    void deleteInstructorDetailsById(Integer instructorDetailsId);
}
