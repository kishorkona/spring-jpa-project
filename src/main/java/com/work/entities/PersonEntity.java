package com.work.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Person")
@Data
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "age")
    private Integer age;

    // Optional: for a bidirectional relationship
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<NotesEntity> notes;

}
