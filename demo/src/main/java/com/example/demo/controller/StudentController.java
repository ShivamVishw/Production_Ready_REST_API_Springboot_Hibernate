package com.example.demo.controller;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // POST Create
//    @PostMapping
//    public Student createStudent(@RequestBody Student student){
//        return studentService.saveStudent(student);
//    }

    // POST Create with ResponseEntity
//    @PostMapping
//    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student){    // @Valid says that Validation is enabled here or in controller
//        Student savedStudent = studentService.saveStudent(student);
//
//        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
//    }

    // POST Create with ResponseEntity and with DTO
    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody StudentDTO studentDTO){    // @Valid says that Validation is enabled here or in controller

        // This is extra task for dto
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setCourse(studentDTO.getCourse());
        // student.setFees(studentDTO.getFees());

        Student savedStudent = studentService.saveStudent(student);

        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }



    // GET - Get All Service
//    @GetMapping
//    public List<Student> getAllStudents(){
//        return studentService.getAllStudent();
//    }

    // GET - Get All Service with ResponseEntity
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = studentService.getAllStudent();

        return ResponseEntity.ok(students);
    }

    // GET by Id -- Service
//    @GetMapping("/{id}")
//    public Optional<Student> getStudentById(@PathVariable Long id){
//        return studentService.getStudentById(id);
//    }

    // GET by Id -- Service with ResponseEntity
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        Student student = studentService.getStudentById(id)
                // .orElseThrow(() -> new RuntimeException("Student not found"));
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + id));

        return ResponseEntity.ok(student);
    }

    // DELETE
//    @DeleteMapping("/{id}")
//    public String deleteStudent(@PathVariable Long id){
//        studentService.deleteStudent(id);
//        return "Student deleted successfully";
//    }

    // DELETE with ResponseEntity
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    // UPDATE

//    @PutMapping("/{id}")
//    public Student updateStudent(@PathVariable Long id, @RequestBody Student student){
//        return studentService.updateStudent(id, student);
//    }

    // UPDATE with ResponseEntity
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid @RequestBody Student student){   // ⚠ Without @Valid, validation will NOT trigger.
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    // Pagination Controller
    // GET http://localhost:8080/api/students/paged?page=0&size=3
    // GET http://localhost:8080/api/students/paged?page=0&size=10&sort=id,desc
    @GetMapping("/paged")
    public ResponseEntity<Page<Student>> getStudents(Pageable pageable){
        Page<Student> studentPage = studentService.getStudentsWithPagination(pageable);

        return ResponseEntity.ok(studentPage);
    }

}






// 🚀 What is ResponseEntity in Spring Boot?
//
//ResponseEntity<T> is a class in Spring that represents:
//
//Complete HTTP response (Body + Status Code + Headers)
//
//Instead of returning just data, it allows you to control:
//
//✅ Response Body
//
//✅ HTTP Status Code
//
//✅ HTTP Headers