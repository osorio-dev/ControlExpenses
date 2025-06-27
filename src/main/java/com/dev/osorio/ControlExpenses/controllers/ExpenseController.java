package com.dev.osorio.ControlExpenses.controllers;

import com.dev.osorio.ControlExpenses.entitys.ExpenseModel;
import com.dev.osorio.ControlExpenses.services.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/allexpenses")
    public ResponseEntity<List<ExpenseModel>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseModel> getExpenseById(@PathVariable("id") Long id) {
        ExpenseModel expenseModel = expenseService.getExpenseById(id);

        if (expenseModel == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(expenseModel);
    }

    @GetMapping("/date")
    public List<ExpenseModel> getExpensesByDateTime(@RequestParam("month") int month) {
        List<ExpenseModel> expenseModelList = expenseService.getAllExpensesByDateTime(month);

        return expenseModelList;
    }

    @PostMapping("/create")
    public ResponseEntity<ExpenseModel> saveExpense(@RequestBody ExpenseModel expenseModel) {
        return ResponseEntity.ok(expenseService.saveExpense(expenseModel));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpenseById(@PathVariable("id") Long id) {
        ExpenseModel expenseModel = expenseService.getExpenseById(id);

        expenseService.deleteExpenseById(expenseModel.getId());

        return ResponseEntity.ok("Despesa Deletada com Sucesso!!");
    }
}
