package com.example.leaflog.bc.til.room.domain;

import com.example.leaflog.bc.sharedkernel.user.vo.UserId;
import com.example.leaflog.bc.til.room.domain.vo.NoteRoomId;
import com.example.leaflog.bc.til.room.domain.vo.NoteRoomName;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@Table(name = "tbl_room")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class NoteRoom {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "note_room_id", nullable = false))
    private NoteRoomId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "user_id", nullable = false, unique = true))
    private UserId userId; //user와 repository는 1:1 관계

    @Embedded
    private NoteRoomName name;

    private String description; //큰 도메인 규칙이 없으므로 일단 원시타입


    public static NoteRoom create(NoteRoomId id,UserId userId, NoteRoomName name, String description){
        return new NoteRoom(id, userId, name, description);
    }
}
