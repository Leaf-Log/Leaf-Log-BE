package com.example.leaflog.bc.profile.profile.presentation;

import com.example.leaflog.bc.profile.profile.application.ProfileQueryService;
import com.example.leaflog.bc.profile.profile.application.query.ProfileReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileQueryService profileQueryService;

    @GetMapping()
    public ProfileReadModel getProfile(){
        return profileQueryService.query();
    }
}
