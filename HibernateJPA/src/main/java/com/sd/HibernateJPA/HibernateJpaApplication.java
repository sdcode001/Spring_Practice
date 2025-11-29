package com.sd.HibernateJPA;

import com.sd.HibernateJPA.dao.StudentDao;
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
    public CommandLineRunner commandLineRunner(StudentDao studentDao){
        return runner -> {
            //saveStudentToDB(studentDao);
            saveManyStudentToDB(studentDao, 5);
            //readStudentById(studentDao, 5);
            //readAllStudents(studentDao);
            //readStudentByLastname(studentDao, "Dey");
            //readStudentByGmail(studentDao);
            //updateStudentLastname(studentDao, "Dey", "Dev");
            //deleteStudentById(studentDao, 6);
        };
    }

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