package com.sd.HibernateJPA;

import com.sd.HibernateJPA.dao.InstructorDao;
import com.sd.HibernateJPA.dao.StudentDao;
import com.sd.HibernateJPA.dao.TutorDao;
import com.sd.HibernateJPA.entity.*;
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
    public CommandLineRunner commandLineRunner(StudentDao studentDao, InstructorDao instructorDao, TutorDao tutorDao){
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

            //One-To-One
            //saveInstructorToDB(instructorDao);
            //showInstructorById(instructorDao);
            //deleteInstructorById(instructorDao);
            //showInstructorDetailsById(instructorDao);
            //deleteInstructorDetailsById(instructorDao);

            //One-TO-Many or Many-To-One
            //saveTutorWithCoursesToDB(tutorDao);
            //showTutorById(tutorDao);
            //showTutorByIdJoinFetch(tutorDao);
            //updateCourse(tutorDao);
            //deleteTutorById(tutorDao);
            //deleteCourseById(tutorDao);

        };
    }

    //------ Advanced Hibernate Mappings Demo methods ------

    private void  saveTutorWithCoursesToDB(TutorDao tutorDao){
        Tutor newTutor = new Tutor("Souvik Dey", "sd@email.com");
        Course course1 = new Course("Core Java Master 3.0");
        Course course2 = new Course("SpringBoot and Hibernate Masterclass");
        newTutor.addCourse(course1);
        newTutor.addCourse(course2);
        System.out.println("Saving Tutor: "+newTutor);
        tutorDao.save(newTutor);
    }

    private void updateTutor(TutorDao tutorDao){
        int id = 2;
        //Fetch Tutor by id
        Tutor tutor = tutorDao.findById(id);
        //updating tutor
        tutor.setName("RANDOM NAME");
        System.out.println("Updating Tutor by id: "+id);
        tutorDao.update(tutor);
        System.out.println("Updated Tutor: "+tutor);
    }

    private void updateCourse(TutorDao tutorDao){
        int id = 2;
        //Fetch Course by id
        Course course = tutorDao.findCourseById(id);
        //updating course
        course.setName("RANDOM NAME");
        System.out.println("Updating Course by id: "+id);
        tutorDao.updateCourse(course);
        System.out.println("Updated Course: "+course);
    }

    private void showTutorById(TutorDao tutorDao){
        int id = 2;
        System.out.println("Finding Tutor by id: "+id);
        Tutor tutor = tutorDao.findById(id);
        System.out.println("Found Tutor: "+tutor);
    }

    private void showTutorByIdJoinFetch(TutorDao tutorDao){
        int id = 2;
        System.out.println("Finding Tutor by id: "+id);
        Tutor tutor = tutorDao.findByIdJoinFetch(id);
        System.out.println("Found Tutor: "+tutor);
        System.out.println("Found associated Courses: "+tutor.getCourses());
    }

    private void deleteTutorById(TutorDao tutorDao){
        int id = 2;
        System.out.println("Deleting Tutor by id: "+id);
        tutorDao.deleteTutorById(id);
        System.out.println("Deleted Tutor with id: "+id);
    }

    private void deleteCourseById(TutorDao tutorDao){
        int id = 3;
        System.out.println("Deleting Course by id: "+id);
        tutorDao.deleteCourseById(id);
        System.out.println("Deleted Course with id: "+id);
    }

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