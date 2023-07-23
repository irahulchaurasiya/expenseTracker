package com.expense.ExpenseTracker.repository;

import com.expense.ExpenseTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IUserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}