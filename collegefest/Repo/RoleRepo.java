package com.greatlearning.collegefest.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.collegefest.entity.Role;

@Repository

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
