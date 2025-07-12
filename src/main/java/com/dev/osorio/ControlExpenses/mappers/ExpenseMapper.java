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

    public ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO, ExpenseModel expenseModel) {

        expenseModel.setId(id);
        expenseModel.setName((expenseDTO.name() == null) ? expenseModel.getName() : expenseDTO.name());
        expenseModel.setCategory((expenseDTO.category() == null) ? expenseModel.getCategory() : expenseDTO.category());
        expenseModel.setValue((expenseDTO.value() == null) ? expenseModel.getValue() : expenseDTO.value());
        expenseModel.setDateTime((expenseDTO.dateTime() == null) ? expenseModel.getDateTime() : expenseDTO.dateTime());

        return toExpenseDTO(expenseModel);
    }
}
