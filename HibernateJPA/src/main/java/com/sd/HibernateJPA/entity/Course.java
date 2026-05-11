package com.sd.HibernateJPA.entity;

import jakarta.persistence.*;


//Demo for Many-To-One relation between tutor and course tables.

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    //Don't cascade delete. If delete a course then don't delete associated tutor
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    //This is the owner for this relation as course table has the Foreign Key refers to PK of tutor
    @JoinColumn(name = "tutor_id", referencedColumnName = "id")
    private Tutor tutor;

    public Course(){ }

    public Course(String name) {
        this.name = name;
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

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tutor_id=" + tutor.getId()+
                '}';
    }
}
