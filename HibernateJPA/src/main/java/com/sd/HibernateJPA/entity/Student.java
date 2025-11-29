package com.sd.HibernateJPA.entity;

import jakarta.persistence.*;

/*
* A entity class must be annotated with @Entity annotation to enable JPA ORM(object to relational mapping)
* Entity class must have default constructor.
*
* Using @Table annotation we cant map the entity class to respective DB table
*
* Using @Column annotation we can map a field to its respective column in the table.
*
* Note- @Table and @Column annotations are optional, If we don't mention them table name will be
*       considered as class name and column names will be considered as field names. But it's not
*       recommended as names can be different, Hence mappings will give error.
*
* Primary Key Annotation:
*       @Id annotation is used to mark a field as primary key and @GeneratedValue is used to specify
*       its value generation strategy.
*       Different strategies- (AUTO, IDENTITY, SEQUENCE, TABLE, UUID).
*       IDENTITY as generation strategy covers most of the use cases on most of the DB tables.
* */

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    public Student(){

    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
