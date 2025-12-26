package com.work.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "department")
@Data
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "department_name")
    private String departmentName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EmployeeEntity> employees;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private LocationEntity location;

}
