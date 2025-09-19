package com.dev.osorio.ControlExpenses.services;

import com.dev.osorio.ControlExpenses.dtos.ExpenseDTO;
import com.dev.osorio.ControlExpenses.entitys.ExpenseModel;
import com.dev.osorio.ControlExpenses.exceptions.InvalidRequestException;
import com.dev.osorio.ControlExpenses.exceptions.NotFoundException;
import com.dev.osorio.ControlExpenses.mappers.ExpenseMapper;
import com.dev.osorio.ControlExpenses.repositorys.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    public ExpenseService(ExpenseRepository expenseRepository, ExpenseMapper expenseMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
    }

    //Get All Expenses
    public List<ExpenseDTO> getAllExpenses() {
        return expenseRepository.findAll().stream()
                .map(expenseMapper::toExpenseDTO)
                .toList();
    }

    //Get Expense ID
    public ExpenseDTO getExpenseById(Long id) {
        Optional<ExpenseModel> expenseModel = expenseRepository.findById(id);

        if (expenseModel.isEmpty()) {
            throw new NotFoundException("Despesa não Encontrada !!!");
        }

        return expenseMapper.toExpenseDTO(expenseModel.get());
    }

    //Get Expense per Name
    public List<ExpenseDTO> getExpenseByName(String name) {
        List<ExpenseModel> expenseModelList = expenseRepository.findByName(name);
        return expenseModelList.stream()
                .map(expenseMapper::toExpenseDTO)
                .sorted()
                .toList();
    }

    //Get Expense per Category
    public List<ExpenseDTO> getExpenseByCategory(String category) {
        List<ExpenseModel> expenseModelList = expenseRepository.findByCategory(category);
        return expenseModelList.stream()
                .map(expenseMapper::toExpenseDTO)
                .sorted(Comparator.comparing(ExpenseDTO::name))
                .toList();
    }

    //Get Expense date day
    public List<ExpenseDTO> getExpenseByDay(String date) {
        LocalDate localDate = dateValidate(date);

        LocalDateTime startDay = localDate.atStartOfDay();
        LocalDateTime startDayTomorrow = localDate.plusDays(1).atStartOfDay();

        List<ExpenseModel> expenseModelList = expenseRepository.findExpensesByDay(startDay, startDayTomorrow);

        return expenseModelList.stream()
                .map(expenseMapper::toExpenseDTO)
                .sorted(Comparator.comparing(ExpenseDTO::dateTime).reversed())
                .toList();
    }

    //Get Expense actually month
    public List<ExpenseDTO> getExpenseByMonth(String date) {
        LocalDate localDate = dateValidate(date);

        Integer year = localDate.getYear();
        Integer monthValue = localDate.getMonthValue();

        List<ExpenseModel> expenseModelList = expenseRepository.findExpensesByMonth(year, monthValue);

        return expenseModelList.stream()
                .map(expenseMapper::toExpenseDTO)
                .sorted(Comparator.comparing(ExpenseDTO::dateTime).reversed())
                .toList();
    }

    //Post Save Expense
    @Transactional
    public ExpenseDTO saveExpense(ExpenseDTO expenseDTO) {
        try {
            ExpenseModel expenseModel = expenseMapper.toExpenseModel(expenseDTO);
            return expenseMapper.toExpenseDTO(expenseRepository.saveAndFlush(expenseModel));
        } catch (Exception e) {
            throw new InvalidRequestException("Dados incompletos e/ou Invalidos!!");
        }
    }

    //Put - Update Expense
    @Transactional
    public ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO) {
        Optional<ExpenseModel> expenseModel = expenseRepository.findById(id);

        if (expenseModel.isEmpty()) {
            throw new NotFoundException("Erro ao Atualizar. A Despesa não Existe nos Registros!!");
        }

        ExpenseDTO expense = expenseMapper.updateExpense(id, expenseDTO, expenseModel.get());

        return saveExpense(expense);
    }

    //Delete Expense
    @Transactional
    public void deleteExpenseById(Long id) {
        Optional<ExpenseModel> expenseModel = expenseRepository.findById(id);

        if (expenseModel.isEmpty()) {
            throw new NotFoundException("Erro ao Deletar. A Despesa não Existe nos Registros!!");
        }

        expenseRepository.deleteById(expenseModel.get().getId());
    }

    private LocalDate dateValidate(String date) {
        try {
            return LocalDate.parse(date);
        } catch (Exception e) {
            throw new InvalidRequestException("Erro, Data Invalida!!");
        }
    }
}
