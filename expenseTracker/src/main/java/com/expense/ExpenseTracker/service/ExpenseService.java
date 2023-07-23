package com.expense.ExpenseTracker.service;

import com.expense.ExpenseTracker.model.Expense;
import com.expense.ExpenseTracker.repository.IExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private IExpenseRepo expenseRepository;

    public Expense createExpense(Expense expense) {

        return expenseRepository.save(expense);
    }

    public Expense getExpenseById(Long expenseId) {
        return expenseRepository.findById(expenseId).orElse(null);
    }

    public Expense updateExpense(Long expenseId, Expense updatedExpense) {
        Expense expense = expenseRepository.findById(expenseId).orElse(null);
        if (expense != null) {

            expense.setTitle(updatedExpense.getTitle());
            expense.setDescription(updatedExpense.getDescription());
            expense.setPrice(updatedExpense.getPrice());
            expense.setDate(updatedExpense.getDate());

            return expenseRepository.save(expense);
        } else {
            return null;
        }
    }

    public boolean deleteExpense(Long expenseId) {
        if (expenseRepository.existsById(expenseId)) {
            expenseRepository.deleteById(expenseId);
            return true;
        } else {
            return false;
        }
    }



    public List<Expense> getExpensesByDate(LocalDate date) {
        return expenseRepository.findByDate(date);
    }

    public double getTotalExpenditureForMonth(LocalDate startDate, LocalDate endDate, Long userId) {
        List<Expense> expenses = expenseRepository.findByUserAndDate(userId, startDate, endDate);

        double totalExpenditure = 0.0;
        for (Expense expense : expenses) {
            totalExpenditure += expense.getAmount();
        }

        return totalExpenditure;
    }
}