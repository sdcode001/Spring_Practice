package com.sd.HibernateJPA.entity;

import jakarta.persistence.*;


//Demo for One-To-One relation between instructor and instructor_details tables.

@Entity
@Table(name = "instructor_details")
public class InstructorDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "hobby")
    private String hobby;

    //Define BiDirectional relation with mappedBy attribute and instructorDetails field of Instructor.
    //Only excluding REMOVE from cascading.
    @OneToOne(mappedBy = "instructorDetails",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Instructor instructor;

    public InstructorDetails(){ }

    public InstructorDetails(String hobby) {
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "InstructorDetails{" +
                "id=" + id +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
