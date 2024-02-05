package com.techsteed.TaskManager.repository;

import com.techsteed.TaskManager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
