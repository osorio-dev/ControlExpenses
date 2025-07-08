package com.dev.osorio.ControlExpenses.mappers;

import com.dev.osorio.ControlExpenses.dtos.ExpenseDTO;
import com.dev.osorio.ControlExpenses.entitys.ExpenseModel;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {

    public ExpenseModel toExpenseModel(ExpenseDTO expenseDTO) {
        ExpenseModel expenseModel = new ExpenseModel();

        expenseModel.setId(expenseDTO.id());
        expenseModel.setName(expenseDTO.name());
        expenseModel.setValue(expenseDTO.value());
        expenseModel.setCategory(expenseDTO.category());
        expenseModel.setDateTime(expenseDTO.dateTime());

        return expenseModel;
    }

    public ExpenseDTO toExpenseDTO(ExpenseModel expenseModel) {

        return new ExpenseDTO(
                expenseModel.getId(),
                expenseModel.getName(),
                expenseModel.getValue(),
                expenseModel.getCategory(),
                expenseModel.getDateTime()
        );
    }
}
