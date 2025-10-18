package com.example.leaflog.bc.til.room.presentation.adapter.in;

import com.example.leaflog.bc.til.room.application.dto.NoteRoomRequest;
import com.example.leaflog.bc.til.room.application.dto.NoteRoomResponse;
import com.example.leaflog.bc.til.room.application.port.in.CreateNoteRoomUseCase;
import com.example.leaflog.bc.til.room.application.port.in.NoteRoomQueryUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class NoteRoomController {

    private final CreateNoteRoomUseCase createNoteRoomUseCase;
    private final NoteRoomQueryUseCase noteRoomQueryUseCase;

    @PostMapping
    public void create(@RequestBody @Valid NoteRoomRequest request){
        createNoteRoomUseCase.create(request);
    }

    @GetMapping
    public NoteRoomResponse query(){
        return noteRoomQueryUseCase.query();
    }
}
