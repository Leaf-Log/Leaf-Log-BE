package com.example.leaflog.bc.til.room.domain.repository;

import com.example.leaflog.bc.sharedkernel.user.vo.UserId;
import com.example.leaflog.bc.til.room.domain.NoteRoom;
import com.example.leaflog.bc.til.room.domain.vo.NoteRoomId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface NoteRoomRepository extends JpaRepository<NoteRoom, NoteRoomId> {
    boolean existsByUserId(UserId userId);

    Optional<NoteRoom> findByUserId(UserId userId);
}
