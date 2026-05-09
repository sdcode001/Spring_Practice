package com.sd.HibernateJPA.entity;

import jakarta.persistence.*;


//Demo for One-To-One relation between instructor and instructor_details tables.

@Entity
@Table(name = "instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    //Defining Relational between Instructor and InstructorDetails
    @OneToOne(cascade = CascadeType.ALL)
    //Linking Instructor with InstructorDetails
    @JoinColumn(name = "instructor_detail_id", referencedColumnName = "id")
    private InstructorDetails instructorDetails;

    public Instructor(){ }

    public Instructor(String name, String email) {
        this.name = name;
        this.email = email;
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

    public InstructorDetails getInstructorDetails() {
        return instructorDetails;
    }

    public void setInstructorDetails(InstructorDetails instructorDetails) {
        this.instructorDetails = instructorDetails;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetails=" + instructorDetails +
                '}';
    }
}
