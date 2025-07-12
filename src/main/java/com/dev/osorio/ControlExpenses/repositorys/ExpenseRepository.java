package com.dev.osorio.ControlExpenses.repositorys;

import com.dev.osorio.ControlExpenses.entitys.ExpenseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseModel, Long> {


    List<ExpenseModel> findByName(String name);

    List<ExpenseModel> findByCategory(String category);

    @Query("SELECT e FROM ExpenseModel e WHERE e.dateTime >= :startDay AND e.dateTime < :startDayTomorrow")
    List<ExpenseModel> findExpensesByDay(
            @Param("startDay") LocalDateTime startDay,
            @Param("startDayTomorrow") LocalDateTime startDayTomorrow
    );

    @Query("SELECT e FROM ExpenseModel e WHERE EXTRACT(MONTH FROM e.dateTime) = :month AND EXTRACT(YEAR FROM e.dateTime) = :year")
    List<ExpenseModel> findExpensesByMonth(@Param("year") Integer year, @Param("month") Integer month);
}
