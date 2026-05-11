package com.sd.HibernateJPA.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


//Demo for One-To-Many relation between tutor and course tables.

@Entity
@Table(name = "tutor")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    //Don't cascade delete. If delete a tutor then don't delete associated courses
    //Define BiDirectional relation with mappedBy attribute
    @OneToMany(
            mappedBy = "tutor",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private List<Course> courses;

    public Tutor(){ }

    public Tutor(String name, String email) {
        this.name = name;
        this.email = email;
    }

    //convenience method for bi-directional relationship
    public void addCourse(Course course){
        if(this.courses == null){
            this.courses = new ArrayList<>();
        }
        //link tutor to the course
        course.setTutor(this);
        this.courses.add(course);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
