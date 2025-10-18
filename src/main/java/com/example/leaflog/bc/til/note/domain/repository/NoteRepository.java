package com.example.leaflog.bc.til.note.domain.repository;

import com.example.leaflog.bc.sharedkernel.user.vo.UserId;
import com.example.leaflog.bc.til.note.domain.Note;
import com.example.leaflog.bc.til.note.domain.vo.NoteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, NoteId> {
    List<Note> findAllByWriterId(UserId writerId);
}
