package com.example.leaflog.bc.profile.profile.presentation.adapter.in.web;

import com.example.leaflog.bc.profile.profile.application.port.in.ProfileQueryUseCase;
import com.example.leaflog.bc.profile.profile.application.dto.ProfileReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileQueryUseCase profileQueryUseCase;

    @GetMapping()
    public ProfileReadModel getProfile(){
        return profileQueryUseCase.query();
    }
}
