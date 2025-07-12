package com.dev.osorio.ControlExpenses.controllers;

import com.dev.osorio.ControlExpenses.dtos.ExpenseDTO;
import com.dev.osorio.ControlExpenses.services.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expense")
@Tag(name = "Expense Management", description = "APIs for managing user expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Operation(summary = "Get All Expenses", description = "Get all the data from the database")
    @ApiResponse(responseCode = "200", description = "Successfully")
    @GetMapping("/allexpenses")
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @Operation(summary = "Get Expense by ID", description = "Get an expense by the selected ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expense Found",
            content = @Content(schema = @Schema(implementation = ExpenseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Expense Not Found")
    })
    @GetMapping("/selectedid")
    public ResponseEntity<ExpenseDTO> getExpenseById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @Operation(summary = "Get Expense by Name", description = "Get an expense by the selected name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expense Found",
            content = @Content(schema = @Schema(implementation = ExpenseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Expense Not Found")
    })
    @GetMapping("/name")
    public ResponseEntity<List<ExpenseDTO>> getExpenseByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(expenseService.getExpenseByName(name));
    }

    @Operation(summary = "Get Expense by Category", description = "Get an expense by the selected category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expense Found",
                    content = @Content(schema = @Schema(implementation = ExpenseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Expense Not Found")
    })
    @GetMapping("/category")
    public ResponseEntity<List<ExpenseDTO>> getExpenseByCategory(@RequestParam(value = "category") String category) {
        return ResponseEntity.ok(expenseService.getExpenseByCategory(category));
    }

    @Operation(summary = "Get Expense by Month",
            description = "Get all the expenses that have the same date related to the month that is being requested.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expenses Found"),
            @ApiResponse(responseCode = "400", description = "Invalid request date")
    })
    @GetMapping("/month")
    public ResponseEntity<List<ExpenseDTO>> getExpensesByMonth(
            @RequestParam(value = "date", required = false) String date
    ){
        return ResponseEntity.ok(
                expenseService.getExpenseByMonth((date != null) ? date : LocalDate.now().toString())
        );
    }

    @Operation(summary = "Get Expense by day",
            description = "Get all the expenses that are recorded with the day that is being requested.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expenses Found"),
            @ApiResponse(responseCode = "400", description = "Invalid request day")
    })
    @GetMapping("/day")
    public ResponseEntity<List<ExpenseDTO>> getExpenseByDay(@RequestParam("date") String date){
        return ResponseEntity.ok(expenseService.getExpenseByDay(date));
    }

    @Operation(summary = "Create a new Expense", description = "Add a new Expense to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expense created successfully",
            content = @Content(schema = @Schema(implementation = ExpenseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
            content = @Content(schema = @Schema()))
    })
    @PostMapping("/create")
    public ResponseEntity<ExpenseDTO> saveExpense(@RequestBody ExpenseDTO expenseDTO) {
        return ResponseEntity.ok(expenseService.saveExpense(expenseDTO));
    }

    @Operation(summary = "Update Expense", description = "Update an existing expense")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expense updated successfully",
            content = @Content(schema = @Schema(implementation = ExpenseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Expense not found",
            content = @Content(schema = @Schema()))
    })
    @PutMapping("/update")
    public ResponseEntity<String> updateExpense(@RequestParam("id") Long id, @RequestBody ExpenseDTO expenseDTO) {
        ExpenseDTO expense = expenseService.updateExpense(id, expenseDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body("A Despesa " + expense.name() + " Foi Atualizada com Sucesso!!!");
    }

    @Operation(summary = "Delete Expense", description = "Deletes an expense from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expense delete successfully"),
            @ApiResponse(responseCode = "404", description = "Expense not found")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteExpenseById(@RequestParam("id") Long id) {
        expenseService.deleteExpenseById(id);
        return ResponseEntity.ok("Despesa Deletada com Sucesso!!");
    }
}
