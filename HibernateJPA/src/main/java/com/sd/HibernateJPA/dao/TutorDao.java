package com.sd.HibernateJPA.dao;

import com.sd.HibernateJPA.entity.Course;
import com.sd.HibernateJPA.entity.Tutor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class TutorDao implements ITutorDao{
    private final EntityManager entityManager;

    @Autowired
    public TutorDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Tutor tutor) {
        try{
            //NOTE- This will insert associated courses also to DB as cascade has PERSIST
            this.entityManager.persist(tutor);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Tutor findById(Integer id) {
        //Default loading for OneToMany is Lazy. Hence It'll only fetch Tutor data not courses data.
        return this.entityManager.find(Tutor.class, id);
    }

    @Override
    public Course findCourseById(Integer id) {
        //Default loading for ManyToOne is Eager. Hence It'll only fetch course data and associated tutor data.
        return this.entityManager.find(Course.class, id);
    }

    //Join Fetch technique, basically this will mimic eager loading
    @Override
    public Tutor findByIdJoinFetch(Integer id) {
        TypedQuery<Tutor> query = this.entityManager.createQuery("select t from Tutor t JOIN FETCH t.courses where t.id = :data", Tutor.class);
        query.setParameter("data", id);
        Tutor result = query.getSingleResult();
        return result;
    }

    @Override
    @Transactional
    public void update(Tutor updatedTutor) {
        try{
            this.entityManager.merge(updatedTutor);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        try{
            this.entityManager.merge(course);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteTutorById(Integer id) {
        //retrieve the Tutor
        Tutor tutor = findByIdJoinFetch(id);

        List<Course> courses = tutor.getCourses();

        //break the association of all courses for tutor
        //if we don't break association, remove() will throw exception for FK constraint violation.
        for(Course tmpCourse: courses){
            tmpCourse.setTutor(null);
        }

        //delete the tutor, This will not delete associated courses as no cascading for delete is defined
        this.entityManager.remove(tutor);
    }

    @Override
    @Transactional
    public void deleteCourseById(Integer id) {
        Course course = findCourseById(id);
        //remove the association of course from courses for tutor
        course.getTutor().getCourses().remove(course);
        //delete the course, This will not delete associated tutor as no cascading for delete is defined
        this.entityManager.remove(course);
    }

}
