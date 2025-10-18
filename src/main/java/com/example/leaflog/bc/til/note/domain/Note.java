package com.example.leaflog.bc.til.note.domain;

import com.example.leaflog.bc.sharedkernel.user.vo.UserId;
import com.example.leaflog.bc.til.note.domain.vo.NoteId;
import com.example.leaflog.bc.til.note.domain.vo.Title;
import com.example.leaflog.bc.til.room.domain.vo.NoteRoomId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "tbl_note")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Note {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "not_id", nullable = false))
    private NoteId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "note_room_id", nullable = false))
    private NoteRoomId noteRoomId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "user_id", nullable = false))
    private UserId writerId;

    @Embedded
    private Title title;

    private String content;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;


    public static Note create(NoteId id, NoteRoomId noteRoomId, UserId writerId, Title title, String content){
        return new Note(id, noteRoomId, writerId, title, content, LocalDateTime.now(), LocalDateTime.now());
    }

    public void edit(String content, UserId writerId) {
        if (!this.writerId.equals(writerId)){
            throw new RuntimeException("이 글을 작성한 작성자와 다릅니다.");
        }
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }
}
