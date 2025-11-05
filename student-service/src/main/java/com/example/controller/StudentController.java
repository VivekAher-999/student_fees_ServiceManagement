
package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Student;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	private final StudentRepository repo;

	@Autowired
	private StudentService studentService;

	public StudentController(StudentRepository repo) {
		this.repo = repo;
	}

	@PostMapping
	public Student addStudent(@RequestBody Student s) {
		return repo.save(s);
	}

	@GetMapping
	public List<Student> getAll() {
		return repo.findAll();
	}

	@GetMapping("/{id}")
	public Student getById(@PathVariable Long id) {
		return repo.findById(id).orElse(null);
	}

	@GetMapping("/student/{studentId}")
    public ResponseEntity<Student> getStudentByStudentId(@PathVariable String studentId) {
        Student student = studentService.getStudentByStudentId(studentId);
        return ResponseEntity.ok(student);
    }
}

