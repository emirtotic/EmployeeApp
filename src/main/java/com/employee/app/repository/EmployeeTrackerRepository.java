package com.employee.app.repository;

import com.employee.app.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeTrackerRepository extends JpaRepository<Employee, Long> {

    @Query("select emp from Employee emp where " +
            "(:personalId is null or emp.personalId = :personalId) and " +
            "(:name is null or emp.name = :name) and " +
            "(:team is null or emp.team = :team) and " +
            "(:teamLead is null or emp.teamLead = :teamLead)")
    List<Employee> findByFilter(
            @Param("personalId") Long personalId,
            @Param("name") String name,
            @Param("team") String team,
            @Param("teamLead") String teamLead);
}