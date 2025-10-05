package com.example.leaflog.bc.account.auth.presentation;

import com.example.leaflog.bc.account.auth.application.ReissueService;
import com.example.leaflog.bc.account.auth.application.dto.TokenReissueRequest;
import com.example.leaflog.bc.account.auth.application.dto.TokenResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final ReissueService reissueService;


    @PostMapping("/reissue")
    public TokenResponseDto reissue(@RequestBody @Valid TokenReissueRequest request){
        return reissueService.reissue(request);
    }
}
