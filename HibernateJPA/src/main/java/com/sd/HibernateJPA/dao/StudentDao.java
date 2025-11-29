package com.sd.HibernateJPA.dao;

import com.sd.HibernateJPA.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


/*
* EntityManager is the main class of JPA which handles all DB operations. We can use the
* bean of this by dependency injection.
* */

/*
* JPQL(JPA query language):
*   It's query language for retrieving data objects, similar to SQL. But JPQL is based on
*   entity class and fields names instead on table and column name.
* */

@Repository
public class StudentDao implements IStudentDao{
    private final EntityManager entityManager;

    @Autowired
    public StudentDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    /*
    * In Spring JPA any transactional DB operation(Insert/update) should marked as transaction with
    * @Transactional It manages transaction automatically and ensures atomicity of DB operations
    *  within the transactional scope.
    * */
    @Override
    @Transactional
    public void save(Student student) {
        System.out.println("Saving Student....");
        try{
            //persist() performs insert operation
            this.entityManager.persist(student);
            /* After insert persist() automatically set the insert id(primary key) value to
             * the @Id field of the given object.
             */
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Student findById(Integer id) {
        return this.entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        //Here it's strict JPQL
        TypedQuery<Student> query = this.entityManager.createQuery("select s from Student s order by s.lastName desc", Student.class);
        List<Student> result = query.getResultList();
        return result;
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        //Using JPQL names parameters(prefixed with a colon) in query
        TypedQuery<Student> query = this.entityManager.createQuery("select s from Student s where s.lastName=:lastname", Student.class);
        query.setParameter("lastname", lastName);
        List<Student> result = query.getResultList();
        return result;
    }

    @Override
    public List<Student> findByGmail() {
        TypedQuery<Student> query = this.entityManager.createQuery("select s from Student s where s.email like '%gmail.com'", Student.class);
        List<Student> result = query.getResultList();
        return result;
    }

    @Override
    @Transactional
    public int updateLastName(String oldLastName, String newLastName) {
        //JSQL update query and this method should be transactional
        Query query = this.entityManager.createQuery("update Student set lastName=:newLastname where lastName=:oldLastname");
        query.setParameter("newLastname", newLastName);
        query.setParameter("oldLastname", oldLastName);
        int numRowsUpdated = query.executeUpdate();
        return numRowsUpdated;
    }

    @Override
    @Transactional
    public int deleteById(Integer id) {
        //JSQL delete query and this method should be transactional
        Query query = this.entityManager.createQuery("delete from Student where id=:targetId");
        query.setParameter("targetId", id);
        int numRowsUpdated = query.executeUpdate();
        return numRowsUpdated;
    }


}
