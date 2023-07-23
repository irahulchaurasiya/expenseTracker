package com.expense.ExpenseTracker.repository;

import com.expense.ExpenseTracker.model.Expense;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IExpenseRepo extends JpaRepository<Expense, Long> {
    @Transactional
    @Modifying
    @Query("SELECT e FROM Expense e WHERE e.date = :date")
    List<Expense> findByDate(@Param("date") LocalDate date);

    @Transactional
    @Modifying
    @Query("SELECT e FROM Expense e WHERE e.user.id = :userId AND e.date >= :startDate AND e.date <= :endDate")
    List<Expense> findByUserAndDate(@Param("userId") Long userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}