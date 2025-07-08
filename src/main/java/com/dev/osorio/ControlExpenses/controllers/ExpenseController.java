package com.dev.osorio.ControlExpenses.controllers;

import com.dev.osorio.ControlExpenses.dtos.ExpenseDTO;
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

    @GetMapping("/allexpenses")
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/name")
    public ResponseEntity<List<ExpenseDTO>> getExpenseByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(expenseService.getExpenseByName(name));
    }

    @GetMapping("/category")
    public ResponseEntity<List<ExpenseDTO>> getExpenseByCategory(@RequestParam(value = "category") String category) {
        return ResponseEntity.ok(expenseService.getExpenseByCategory(category));
    }

    @GetMapping()
    public ResponseEntity<List<ExpenseDTO>> getExpensesByMonth(
            @RequestParam(value = "date", required = false) String date
    ){
        return ResponseEntity.ok(
                expenseService.getExpenseByMonth((date != null) ? date : LocalDate.now().toString())
        );
    }

    @GetMapping("/day")
    public ResponseEntity<List<ExpenseDTO>> getExpenseByDay(@RequestParam("date") String date){
        return ResponseEntity.ok(expenseService.getExpenseByDay(date));
    }

    @PostMapping("/create")
    public ResponseEntity<ExpenseDTO> saveExpense(@RequestBody ExpenseDTO expenseDTO) {
        return ResponseEntity.ok(expenseService.saveExpense(expenseDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteExpenseById(@RequestParam("id") Long id) {
        expenseService.deleteExpenseById(id);
        return ResponseEntity.ok("Despesa Deletada com Sucesso!!");
    }
}
