package com.example.leaflog.bc.til.note.presentation.adapter.in;

import com.example.leaflog.bc.til.note.application.dto.NoteRequest;
import com.example.leaflog.bc.til.note.application.dto.NoteResponse;
import com.example.leaflog.bc.til.note.application.port.in.CreateNoteUseCase;
import com.example.leaflog.bc.til.note.application.port.in.DeleteNoteUseCase;
import com.example.leaflog.bc.til.note.application.port.in.UpdateNoteUseCase;
import com.example.leaflog.bc.til.note.application.service.NoteQueryService;
import com.example.leaflog.bc.til.room.application.dto.NoteUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteController {

    private final CreateNoteUseCase CreateNoteUseCase;
    private final UpdateNoteUseCase updateNoteUseCase;
    private final DeleteNoteUseCase deleteNoteUseCase;
    private final NoteQueryService noteQueryService;

    @PostMapping
    public void create(@RequestBody @Valid NoteRequest request){
        CreateNoteUseCase.create(request);
    }

    @PatchMapping("/{noteId}")
    public void update(@RequestBody @Valid NoteUpdateRequest request, @PathVariable("noteId") String noteId){
        updateNoteUseCase.update(request, noteId);
    }

    @DeleteMapping("/{noteId}")
    public void delete(@PathVariable("noteId") String noteId){
        deleteNoteUseCase.delete(noteId);
    }

    @GetMapping("/{noteId}")
    public NoteResponse query(@PathVariable("noteId") String noteId){
        return noteQueryService.query(noteId);
    }

    @GetMapping()
    public List<NoteResponse> queryAll(){
        return noteQueryService.queryAll();
    }
}
