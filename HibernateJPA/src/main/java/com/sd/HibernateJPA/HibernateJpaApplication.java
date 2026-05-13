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

            //One-To-Many or Many-To-One
            //saveTutorWithCoursesToDB(tutorDao);
            //showTutorById(tutorDao);
            //showTutorByIdJoinFetch(tutorDao);
            //updateCourse(tutorDao);
            //deleteTutorById(tutorDao);
            //deleteCourseById(tutorDao);
            //saveCourseWithReviewsToDB(tutorDao);
            //showCourseByIdJoinFetch(tutorDao);
            //deleteCourseAndReviewsById(tutorDao);

            //Many-To-Many
            //saveStudentWithSubjectsToDB(studentDao);
            //showStudentWithSubjectsById(studentDao);
            //showSubjectWithStudentsById(studentDao);
            //addStudentByUpdatingSubject(studentDao);
            //deleteStudentById(studentDao);
            //deleteSubjectById(studentDao);

        };
    }

    //------ Advanced Hibernate Mappings Demo methods ------

    private void deleteSubjectById(StudentDao studentDao){
        int subjectId = 3;
        System.out.println("Deleting Subject by id: "+subjectId);
        studentDao.deleteSubjectById(subjectId);
        System.out.println("Deleted Subject with id: "+subjectId);
    }

    private void deleteStudentById(StudentDao studentDao){
        int studentId = 13;
        System.out.println("Deleting Student by id: "+studentId);
        studentDao.deleteStudentById(studentId);
        System.out.println("Deleted Student with id: "+studentId);
    }

    private void addStudentByUpdatingSubject(StudentDao studentDao){
        int subjectId = 1;
        Subject subject = studentDao.findSubjectWithStudentsById(subjectId);
        Student student1 = new Student("Abhay", "Singh", "as@emailcon");
        Student student2 = new Student("Deep", "Modak", "dm@emailcon");
        subject.addStudent(student1);
        subject.addStudent(student2);
        System.out.println("Adding Students by Subject update: "+subject);
        System.out.println("Students to be added: "+subject.getStudents());
        studentDao.updateSubject(subject);
    }

    private void showSubjectWithStudentsById(StudentDao studentDao){
        int id = 1;
        System.out.println("Finding Subject by id: "+id);
        Subject subject = studentDao.findSubjectWithStudentsById(id);
        System.out.println("Found Subject: "+subject);
        System.out.println("Found associated Students: "+subject.getStudents());
    }

    private void showStudentWithSubjectsById(StudentDao studentDao){
        int id = 6;
        System.out.println("Finding Student by id: "+id);
        Student student = studentDao.findStudentWithSubjectsById(id);
        System.out.println("Found Student: "+student);
        System.out.println("Found associated Subjects: "+student.getSubjects());
    }

    private void saveStudentWithSubjectsToDB(StudentDao studentDao){
        Student newStudent = new Student("Souvik", "Dey", "sd@email.com");
        Subject sub1 = new Subject("Math");
        Subject sub2 = new Subject("Physics");
        newStudent.addSubject(sub1);
        newStudent.addSubject(sub2);
        System.out.println("Saving Student: "+newStudent);
        System.out.println("Saving associated subjects: "+newStudent.getSubjects());
        studentDao.save(newStudent);
    }

    private void  saveTutorWithCoursesToDB(TutorDao tutorDao){
        Tutor newTutor = new Tutor("Souvik Dey", "sd@email.com");
        Course course1 = new Course("Core Java Master 3.0");
        Course course2 = new Course("SpringBoot and Hibernate Masterclass");
        newTutor.addCourse(course1);
        newTutor.addCourse(course2);
        System.out.println("Saving Tutor: "+newTutor);
        tutorDao.save(newTutor);
    }

    private void  saveCourseWithReviewsToDB(TutorDao tutorDao){
        Course newCourse = new Course("Ultimate SpringBoot 3.0");
        Review review1 = new Review("Its a very good course");
        Review review2 = new Review("This course is underrated");
        newCourse.addReview(review1);
        newCourse.addReview(review2);
        System.out.println("Saving Course: "+newCourse);
        System.out.println("Saving associated reviews: "+newCourse.getReviews());
        tutorDao.saveCourse(newCourse);
    }

    private void showCourseByIdJoinFetch(TutorDao tutorDao){
        int id = 5;
        System.out.println("Finding Course by id: "+id);
        Course course = tutorDao.findCourseByIdJoinFetch(id);
        System.out.println("Found Course: "+course);
        System.out.println("Found associated Reviews: "+course.getReviews());
    }

    private void deleteCourseAndReviewsById(TutorDao tutorDao){
        int courseId = 5;
        System.out.println("Deleting Course by id: "+courseId);
        tutorDao.deleteCourseById(courseId);
        System.out.println("Deleted Course with id: "+courseId);
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