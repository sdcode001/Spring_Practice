package com.sd.HibernateJPA.dao;

import com.sd.HibernateJPA.entity.Instructor;
import com.sd.HibernateJPA.entity.InstructorDetails;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class InstructorDao implements IInstructorDao{
    private EntityManager entityManager;

    @Autowired
    public InstructorDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        try{
            //NOTE- This will insert associated InstructorDetails also to DB as cascade = CascadeType.ALL
            this.entityManager.persist(instructor);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Instructor findById(Integer id) {
        //NOTE- This will fetch InstructorDetails also as default behaviour of @OneToOne is eager loading.
        return this.entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Instructor instructor = findById(id);
        try{
            //NOTE- This will delete associated InstructorDetails also from DB as cascade = CascadeType.ALL
            this.entityManager.remove(instructor);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public InstructorDetails findInstructorDetailsById(Integer instructorDetailsId) {
        return this.entityManager.find(InstructorDetails.class, instructorDetailsId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailsById(Integer instructorDetailsId) {
        InstructorDetails instructorDetails = findInstructorDetailsById(instructorDetailsId);

        try{
            //As we're not cascading REMOVE, So associated instructor will not be deleted from DB, Hence breaking BiDirectional link
            instructorDetails.getInstructor().setInstructorDetails(null);

            this.entityManager.remove(instructorDetails);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }


}
