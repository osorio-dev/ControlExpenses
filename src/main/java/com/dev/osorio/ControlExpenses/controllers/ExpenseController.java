package com.dev.osorio.ControlExpenses.controllers;

import com.dev.osorio.ControlExpenses.entitys.ExpenseModel;
import com.dev.osorio.ControlExpenses.services.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping()
    public ResponseEntity<List<ExpenseModel>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/name")
    public ResponseEntity<List<ExpenseModel>> getExpenseByName(@RequestParam(value = "name") String name) {
        List<ExpenseModel> expenseModelList = expenseService.getExpenseByName(name);
        return ResponseEntity.ok(expenseModelList);
    }

    @GetMapping("/category")
    public ResponseEntity<List<ExpenseModel>> getExpenseByCategory(@RequestParam(value = "category") String category) {
        List<ExpenseModel> expenseModelList = expenseService.getExpenseByCategory(category);
        return ResponseEntity.ok(expenseModelList);
    }

    @GetMapping("/date")
    public ResponseEntity<List<ExpenseModel>> getExpensesByDay(@RequestParam("date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<ExpenseModel> expenseModelList = expenseService.getExpenseByDay(localDate);
        return ResponseEntity.ok(expenseModelList);
    }

    @GetMapping("/date/month")
    public ResponseEntity<List<ExpenseModel>> getExpensesByMonth(@RequestParam("date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<ExpenseModel> expenseModelList = expenseService.getExpenseByMonth(localDate);
        return ResponseEntity.ok(expenseModelList);
    }


    @PostMapping("/create")
    public ResponseEntity<ExpenseModel> saveExpense(@RequestBody ExpenseModel expenseModel) {
        return ResponseEntity.ok(expenseService.saveExpense(expenseModel));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpenseById(@PathVariable("id") Long id) {
        expenseService.deleteExpenseById(id);
        return ResponseEntity.ok("Despesa Deletada com Sucesso!!");
    }
}
