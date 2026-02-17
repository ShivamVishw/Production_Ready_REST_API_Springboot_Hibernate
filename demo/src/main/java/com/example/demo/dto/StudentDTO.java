package com.example.demo.dto;

import jakarta.validation.constraints.*;

public class StudentDTO {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Course cannot be empty")
    private String course;

//    @Positive(message = "Fees must be positive")
//    private double fees;

    // Getters and Setters
    public String getName() { return name; }
    public String getCourse() { return course; }
   // public double getFees() { return fees; }

    public void setName(String name) { this.name = name; }
    public void setCourse(String course) { this.course = course; }
    //public void setFees(double fees) { this.fees = fees; }
}
