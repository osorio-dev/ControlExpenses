package com.dev.osorio.ControlExpenses.services;

import com.dev.osorio.ControlExpenses.entitys.ExpenseModel;
import com.dev.osorio.ControlExpenses.exceptions.EventNotFoundException;
import com.dev.osorio.ControlExpenses.repositorys.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    //Get All Expenses
    public List<ExpenseModel> getAllExpenses() {
        return expenseRepository.findAll();
    }

    //Get Expense per ID
    public ExpenseModel getExpenseById(Long id) {
        Optional<ExpenseModel> expenseModel = expenseRepository.findById(id);
        return expenseModel.orElseThrow(EventNotFoundException::new);
    }

    //Get Filter Expense DateTime
    public List<ExpenseModel> getAllExpensesByDateTime(int month) {

        return expenseRepository.findByDate(month);
    }

    //Post Save Expense
    public ExpenseModel saveExpense(ExpenseModel expenseModel) {
        return expenseRepository.saveAndFlush(expenseModel);
    }

    //Delete Expense
    public void deleteExpenseById(Long id) {
        Optional<ExpenseModel> expenseModel = expenseRepository.findById(id);

        expenseModel.ifPresent(expense -> {
            expenseRepository.deleteById(id);
        });
    }
}
