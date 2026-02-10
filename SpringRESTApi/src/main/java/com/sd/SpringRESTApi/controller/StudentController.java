package com.sd.SpringRESTApi.controller;

import com.sd.SpringRESTApi.entity.Student;
import com.sd.SpringRESTApi.exception.StudentNotFoundException;
import com.sd.SpringRESTApi.model.MyErrorResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class StudentController {
    List<Student> studentList = new ArrayList<>();

    //this will be executed after StudentController bean initialization
    @PostConstruct
    public void populateStudents(){
        this.studentList.add(new Student("Souvik", "Dey"));
        this.studentList.add(new Student("Abhay", "Singh"));
        this.studentList.add(new Student("Deep", "Modak"));
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return this.studentList;
    }

    //Here studentId is path variable of this endpoint url
    //Bind path variable to method parameter using @PathVariable
    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId){
        if(studentId < 0 || studentId >= this.studentList.size()){
            //this exception will be handled by ExceptionHandler method and returns suitable ResponseEntity.
            throw new StudentNotFoundException("Student Id not found: "+studentId);
        }

        return this.studentList.get(studentId);
    }

    //Define exception handler method for StudentNotFoundException on this controller with @ExceptionHandler annotation
    //This handler method will return a ResponseEntity
    //ResponseEntity is the wrapper for the custom response object
    @ExceptionHandler
    public ResponseEntity<MyErrorResponse> handleException(StudentNotFoundException exp){
        MyErrorResponse response = new MyErrorResponse();

        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exp.getMessage());
        response.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
