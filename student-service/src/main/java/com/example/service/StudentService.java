package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Student;
import com.example.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository repo;

	public Student getStudentByStudentId(String studentId) {
		return repo.findByStudentId(studentId)
				.orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
	}

}
