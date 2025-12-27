package com.work.repository;

import com.work.entities.DepartmentEntity;
import com.work.entities.EmployeeEntity;
import com.work.entities.NotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findEmployeeByFirstName(String firstName);

    EmployeeEntity findEmployeeByFirstNameAndLastName(String firstName, String lastName);

    @Modifying
    @Query("DELETE FROM EmployeeEntity e WHERE e.firstName = :firstName AND e.lastName = :lastName")
    void deleteByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}