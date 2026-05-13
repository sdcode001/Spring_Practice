package com.sd.HibernateJPA.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


//Demo for Many-To-One Bi-directional relation between tutor and course tables.
//Demo for One-To-Many Uni-directional relation between course and review tables

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

    //This defines Uni-directional OneToMany relation from course to review with all cascading
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //It tells Hibernate to look at course_id column(FK) of review table for linking the relation.
    @JoinColumn(name = "course_id")
    private List<Review> reviews;

    public Course(){ }

    public Course(String name) {
        this.name = name;
    }

    //convenience method for linking Course and Review
    public void addReview(Review review){
        if(this.reviews == null){
            this.reviews = new ArrayList<>();
        }
        this.reviews.add(review);
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
