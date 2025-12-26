package com.work.repository;

import com.work.entities.NotesEntity;
import com.work.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<NotesEntity, Long> {

    List<NotesEntity> findByPersonId(Long personId);
    List<NotesEntity> findByTitle(String title);
    List<NotesEntity> findByTitleAndNotes(String title, String notes);
}