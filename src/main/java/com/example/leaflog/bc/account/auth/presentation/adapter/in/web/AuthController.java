package com.example.leaflog.bc.account.auth.presentation.adapter.in.web;

import com.example.leaflog.bc.account.auth.application.port.in.ReissueUseCase;
import com.example.leaflog.bc.account.auth.application.dto.TokenReissueRequest;
import com.example.leaflog.bc.account.auth.application.dto.TokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final ReissueUseCase reissueUseCase;


    @PostMapping("/reissue")
    public TokenResponse reissue(@RequestBody @Valid TokenReissueRequest request){
        return reissueUseCase.reissue(request);
    }
}
