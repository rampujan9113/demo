package com.qrcode.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.WriterException;
import com.qrcode.model.Student;
import com.qrcode.qrutilis_generate.QRCodeGenerator;
import com.qrcode.service.QrService;
import com.qrcode.service.StudentService;


@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
//	@Autowired
//	private QrService qrService;
//	
//	@Value("${Project.qrcode}")
//	private String path;
//	
//	@Autowired
//	private ObjectMapper mapper;
	
	@GetMapping("/getStudent")
	public ResponseEntity<List<Student>> getStudents() throws WriterException, IOException{
		
		List<Student> students = studentService.getStudents();
		if((students.size())!=0) {
			for(Student student: students) {
				QRCodeGenerator.generateQRCode(student);
			}
		}
		
		return ResponseEntity.ok(studentService.getStudents());
	}
	
	@PostMapping("/addStudent")
	public ResponseEntity<Student> addStudent(@RequestBody Student student){
		return ResponseEntity.ok(studentService.addStudent(student));
	}
	
	@GetMapping("git/{id}")
	public ResponseEntity<Student> findById(@PathVariable("id") int id){
		return ResponseEntity.ok(studentService.findById(id));
       
	}

}
