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
    List<ExpenseModel> findByDate(
            @Param("startDay") LocalDateTime startDay,
            @Param("startDayTomorrow") LocalDateTime startDayTomorrow
    );
}
