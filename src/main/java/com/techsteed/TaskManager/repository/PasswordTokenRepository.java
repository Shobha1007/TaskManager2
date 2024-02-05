package com.techsteed.TaskManager.repository;

import com.techsteed.TaskManager.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken,Long> {
}
