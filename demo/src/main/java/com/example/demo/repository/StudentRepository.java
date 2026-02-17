package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


// JpaRepository<Student, Long>
// Student → Entity class
// Long → Primary key type
// interface

public interface StudentRepository extends JpaRepository<Student, Long> {


}




// Spring automatically provides:
//
//save()
//
//findAll()
//
//findById()
//
//deleteById()
//
//etc.


