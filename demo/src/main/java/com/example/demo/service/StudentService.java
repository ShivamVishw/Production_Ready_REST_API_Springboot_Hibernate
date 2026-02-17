package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Create Student
    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    // Get All Student
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    // Get student by id
    public Optional<Student> getStudentById(Long id){
        return studentRepository.findById(id);
    }

    // Delete Student
    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }

    // Update Student
    public Student updateStudent(Long id, Student updatedStudent){


        // Step 1 is to check whether this id students exist or not
        Student existingStudent = studentRepository.findById(id)
//                .orElseThrow(() ->new RuntimeException("Student not found with id: "+ id));
                  .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + id));  // Better use custom exception instead generic exception

        // Step 2 is to update into existing database with help of repo
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setCourse(updatedStudent.getCourse());
        existingStudent.setFees(updatedStudent.getFees());

        // Step 3 is to save updated student
        return studentRepository.save(existingStudent);
    }

    // Pagination create method
    public Page<Student> getStudentsWithPagination(Pageable pageable){
        return studentRepository.findAll(pageable);
    }


}
