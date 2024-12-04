package com.example.demo.Controller;

import com.example.demo.Entity.Student;
import com.example.demo.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    StudentRepository repo;

    @GetMapping("/students")
    public List<Student> getAllStudent(){
        List<Student> students=repo.findAll();
        return students;
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        Optional<Student> studentOptional = repo.findById(id);
        if (studentOptional.isPresent()) {
            return new ResponseEntity<>(studentOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/students/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createstudent(@RequestBody Student student){
        repo.save(student);
        return student;
    }

    @PutMapping("/students/update/{id}")
    public Student updateStudent(@PathVariable int id){
        Student student=repo.findById(id).get();
        student.setName("Khadeeja");
        student.setPercentage(99.9F);
        repo.save(student);
        return student;


    }

    @DeleteMapping("students/delete/{id}")
    public Student deleteStudent(@PathVariable int id){
        Student student=repo.findById(id).get();
        repo.delete(student);
        return  student;

    }




}
