package com.sd.HibernateJPA.dao;

import com.sd.HibernateJPA.entity.Course;
import com.sd.HibernateJPA.entity.Tutor;


public interface ITutorDao {
    void save(Tutor tutor);
    Tutor findById(Integer id);
    Course findCourseById(Integer id);
    Tutor findByIdJoinFetch(Integer id);
    void update(Tutor updatedTutor);
    void updateCourse(Course course);
    void deleteTutorById(Integer id);
    void deleteCourseById(Integer id);
    void saveCourse(Course course);
    Course findCourseByIdJoinFetch(Integer id);
}
