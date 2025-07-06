package com.dev.osorio.ControlExpenses.services;

import com.dev.osorio.ControlExpenses.entitys.ExpenseModel;
import com.dev.osorio.ControlExpenses.exceptions.NotFoundException;
import com.dev.osorio.ControlExpenses.repositorys.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
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

    //Get Expense per Name
    public List<ExpenseModel> getExpenseByName(String name) {
        return expenseRepository.findByName(name);
    }

    //Get Expense per Category
    public List<ExpenseModel> getExpenseByCategory(String category) {
        return expenseRepository.findByCategory(category);
    }

    //Get Expense date day
    public List<ExpenseModel> getExpenseByDay(LocalDate localDate) {
        LocalDateTime startDay = localDate.atStartOfDay();
        LocalDateTime startDayTomorrow = localDate.plusDays(1).atStartOfDay();

        return expenseRepository.findExpensesByDay(startDay, startDayTomorrow);
    }

    //Get Expense actually month
    public List<ExpenseModel> getExpenseByDateNow(LocalDate localDate) {
        Integer year = localDate.getYear();
        Integer monthValue = localDate.getMonthValue();
        return expenseRepository.findExpensesByMonth(year, monthValue);
    }

    //Get Expenses month user selected
    public List<ExpenseModel> getExpenseBySelectedMonth(Integer month) {
        return expenseRepository.findExpensesByMonth(LocalDate.now().getYear(), month);
    }

    //Post Save Expense
    public ExpenseModel saveExpense(ExpenseModel expenseModel) {
        return expenseRepository.saveAndFlush(expenseModel);
    }

    //Delete Expense
    public void deleteExpenseById(Long id) {
        Optional<ExpenseModel> expenseModel = expenseRepository.findById(id);

        if (expenseModel.isEmpty()) {
            throw new NotFoundException("Erro ao Deletar. A Despesa não Existe nos Registros!!");
        }

        expenseRepository.deleteById(expenseModel.get().getId());
    }
}
