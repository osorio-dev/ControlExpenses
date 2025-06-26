package com.dev.osorio.ControlExpenses.repositorys;

import com.dev.osorio.ControlExpenses.entitys.ExpenseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseModel, Long> {

    @Query("SELECT e FROM ExpenseModel e WHERE EXTRACT(MONTH FROM e.dateTime) = :month")
    List<ExpenseModel> findByDate(@Param("month") int month);
}
