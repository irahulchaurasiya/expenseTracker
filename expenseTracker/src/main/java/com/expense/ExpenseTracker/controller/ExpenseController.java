package com.expense.ExpenseTracker.controller;

import com.expense.ExpenseTracker.model.Expense;
import com.expense.ExpenseTracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        Expense createdExpense = expenseService.createExpense(expense);
        return ResponseEntity.ok(createdExpense);
    }
    @GetMapping("/{expenseId}")
    public ResponseEntity<Expense> getExpense(@PathVariable Long expenseId) {
        Expense expense = expenseService.getExpenseById(expenseId);
        if (expense != null) {
            return ResponseEntity.ok(expense);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{expenseId}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long expenseId, @RequestBody Expense expense) {
        Expense updatedExpense = expenseService.updateExpense(expenseId, expense);
        if (updatedExpense != null) {
            return ResponseEntity.ok(updatedExpense);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long expenseId) {
        boolean deleted = expenseService.deleteExpense(expenseId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/date")
    public ResponseEntity<List<Expense>> getExpensesByDate(@RequestParam LocalDate date) {
        List<Expense> expenses = expenseService.getExpensesByDate(date);
        return ResponseEntity.ok(expenses);
    }
    @GetMapping("/report")
    public ResponseEntity<Double> getTotalExpenditureForMonth(@RequestParam LocalDate From, @RequestParam LocalDate To, @RequestParam Long userId) {
        double totalExpenditure = expenseService.getTotalExpenditureForMonth(From,To, userId);
        return ResponseEntity.ok(totalExpenditure);
    }

}