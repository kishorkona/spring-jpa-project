package com.work.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "Notes")
@EntityListeners(AuditingEntityListener.class)
@Data
public class NotesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String notes;
    private String title;
    private String url;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;

}
