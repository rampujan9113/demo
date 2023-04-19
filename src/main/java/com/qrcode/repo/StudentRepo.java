package com.qrcode.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qrcode.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}
