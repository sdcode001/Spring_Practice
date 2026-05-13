package com.sd.HibernateJPA.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


//Demo for Many-To-Many relation between student and subject

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    //Tells Hibernate to look at subjects field of Student for relation mapping. And don't allow cascade delete.
    @ManyToMany(
            mappedBy = "subjects",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private List<Student> students;

    public Subject(){ }

    public Subject(String name) {
        this.name = name;
    }

    //convenience method for Bi-directional relationship
    public void addStudent(Student student){
        if(this.students == null){
            this.students = new ArrayList<>();
        }
        this.students.add(student);
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
