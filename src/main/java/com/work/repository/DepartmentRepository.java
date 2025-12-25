package com.work.repository;

import com.work.entities.DepartmentEntity;
import com.work.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    DepartmentEntity findByDepartmentName(String departmentName);
}