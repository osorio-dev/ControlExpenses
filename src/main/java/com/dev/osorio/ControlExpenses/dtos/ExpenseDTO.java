package com.dev.osorio.ControlExpenses.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExpenseDTO(
        Long id,
        String name,
        BigDecimal value,
        String category,
        LocalDateTime dateTime
) {}
