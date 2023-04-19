package com.qrcode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qrcode.model.Student;
import com.qrcode.repo.StudentRepo;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepo repo;
	
	public List<Student> getStudents(){
		
		return repo.findAll();
	}
	
	public Student addStudent(Student student) {
		return repo.save(student);
	}

	public Student findById(int id) {
		return repo.findById(id).orElseThrow(()-> new RuntimeException("Student not found"));
	}
}
