package com.greatlearning.collegefest.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.collegefest.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}
