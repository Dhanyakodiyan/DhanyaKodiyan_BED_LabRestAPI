package com.greatlearning.collegefest.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.collegefest.entity.Student;
import com.greatlearning.collegefest.Repo.StudentRepo;

@Service

public class StudentService {
	
	@Autowired
	private StudentRepo StudentRepo;
	
	public void saveStudent(Student theStudent) {
		StudentRepo.save(theStudent);
		
	}
	public Student findStudentById(int id) {
		return StudentRepo.findById(id).get();
	}
	
	public List<Student> fetchAllStudents(){
		return StudentRepo.findAll();
	}
	
	public void deleteById(int id) {
		 StudentRepo.deleteById(id);
	}
}


