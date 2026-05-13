package com.sd.HibernateJPA.dao;

import com.sd.HibernateJPA.entity.Student;
import com.sd.HibernateJPA.entity.Subject;

import java.util.List;

public interface IStudentDao {
    void save(Student student);
    Student findById(Integer id);
    List<Student> findAll();
    List<Student> findByLastName(String lastName);
    List<Student> findByGmail();
    int updateLastName(String oldLastName, String newLastName);
    int deleteById(Integer id);
    Student findStudentWithSubjectsById(Integer studentId);
    Subject findSubjectWithStudentsById(Integer subjectId);
    void updateSubject(Subject subject);
    void deleteStudentById(Integer studentId);
    void deleteSubjectById(Integer subjectId);
}
