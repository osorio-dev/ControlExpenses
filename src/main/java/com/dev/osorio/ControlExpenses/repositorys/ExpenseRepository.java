package com.dev.osorio.ControlExpenses.repositorys;

import com.dev.osorio.ControlExpenses.entitys.ExpenseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseModel, Long> {
}
