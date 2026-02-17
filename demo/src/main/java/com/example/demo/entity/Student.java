package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "students")
public class Student {

    // Make id primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name cannot be empty")     // For validation we can use this
    private String name;

    @NotBlank(message = "Course cannot be empty")
    private String course;

    // @Positive(message = "Fees must be positive")
    private double fees;

    // Constructor
    public Student() {
    }

    public Student(String name, String course, double fees) {

        this.name = name;
        this.course = course;
        this.fees = fees;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }
}
