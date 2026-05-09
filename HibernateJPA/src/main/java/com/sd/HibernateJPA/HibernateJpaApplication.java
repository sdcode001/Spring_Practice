package com.sd.HibernateJPA;

import com.sd.HibernateJPA.dao.InstructorDao;
import com.sd.HibernateJPA.dao.StudentDao;
import com.sd.HibernateJPA.entity.Instructor;
import com.sd.HibernateJPA.entity.InstructorDetails;
import com.sd.HibernateJPA.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class HibernateJpaApplication {

	public static void main(String[]  args) {
		SpringApplication.run(HibernateJpaApplication.class, args);
	}

    //Dependency injection for StudentDao
    @Bean
    public CommandLineRunner commandLineRunner(StudentDao studentDao, InstructorDao instructorDao){
        return runner -> {
            //saveStudentToDB(studentDao);
            //saveManyStudentToDB(studentDao, 5);
            //readStudentById(studentDao, 5);
            //readAllStudents(studentDao);
            //readStudentByLastname(studentDao, "Dey");
            //readStudentByGmail(studentDao);
            //updateStudentLastname(studentDao, "Dey", "Dev");
            //deleteStudentById(studentDao, 6);

            //------ Advanced Hibernate Mappings Demo ------

            //saveInstructorToDB(instructorDao);
            //showInstructorById(instructorDao);
            //deleteInstructorById(instructorDao);
            //showInstructorDetailsById(instructorDao);
            deleteInstructorDetailsById(instructorDao);

        };
    }

    //------ Advanced Hibernate Mappings Demo methods ------

    private void saveInstructorToDB(InstructorDao instructorDao){
        Instructor newInstructor = new Instructor("Deep Modak", "dm@gmail.com");
        InstructorDetails newInstructorDetails = new InstructorDetails("Badminton");
        //associate the objects
        newInstructor.setInstructorDetails(newInstructorDetails);
        //NOTE: This will also save associated InstructorDetails and cascade = CascadeType.ALL
        System.out.println("Saving Instructor: "+newInstructor);
        instructorDao.save(newInstructor);
    }

    private void showInstructorById(InstructorDao instructorDao){
        int id = 1;
        System.out.println("Finding Instructor by id: "+id);
        Instructor instructor = instructorDao.findById(id);
        System.out.println("Found Instructor: "+instructor);
        System.out.println("Found associated InstructorDetails: "+instructor.getInstructorDetails());
    }

    private void deleteInstructorById(InstructorDao instructorDao){
        int id = 1;
        System.out.println("Deleting Instructor by id: "+id);
        instructorDao.deleteById(id);
        System.out.println("Deleted Instructor with id: "+id);
    }

    private void showInstructorDetailsById(InstructorDao instructorDao){
        int id = 2;
        System.out.println("Finding InstructorDetails by id: "+id);
        InstructorDetails instructorDetails = instructorDao.findInstructorDetailsById(id);
        System.out.println("Found InstructorDetails: "+instructorDetails);
        System.out.println("Found associated Instructor: "+instructorDetails.getInstructor());
    }

    private void deleteInstructorDetailsById(InstructorDao instructorDao){
        int id = 2;
        System.out.println("Deleting InstructorDetails by id: "+id);
        instructorDao.deleteInstructorDetailsById(id);
        System.out.println("Deleted InstructorDetails with id: "+id);
    }


    //--------------- Hibernate Demo methods ---------------

    private void saveStudentToDB(StudentDao studentDao){
        Student student = new Student("Anik", "Roy", "anik.roy10@gmail.com");
        studentDao.save(student);
        System.out.println("Saved Student with id: " + student.getId());
    }

    private void saveManyStudentToDB(StudentDao studentDao, int numStudent){
        for(int i=1; i<=numStudent; i++){
            Student student = new Student("Student"+i, "Title"+i, "mail"+i+"@gmail.com");
            studentDao.save(student);
            System.out.println("Saved Student with id: " + student.getId());
        }
    }

    private void readStudentById(StudentDao studentDao, Integer id){
        System.out.println("Result- " + studentDao.findById(id));
    }

    private void readAllStudents(StudentDao studentDao){
        System.out.println("Result- " + studentDao.findAll());
    }

    private void readStudentByLastname(StudentDao studentDao, String lastName){
        System.out.println("Result- " + studentDao.findByLastName(lastName));
    }

    private void readStudentByGmail(StudentDao studentDao){
        System.out.println("Result- " + studentDao.findByGmail());
    }

    private void updateStudentLastname(StudentDao studentDao, String oldLastName, String newLastName){
        System.out.println("Student rows updated: " + studentDao.updateLastName(oldLastName, newLastName));
    }

    private void deleteStudentById(StudentDao studentDao, int id){
        System.out.println("Student rows deleted: " + studentDao.deleteById(id));
    }
}