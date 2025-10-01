package com.example.leaflog.bc.account.auth.presentation;

import com.example.leaflog.bc.account.auth.application.OauthLoginService;
import com.example.leaflog.bc.account.auth.application.ReissueService;
import com.example.leaflog.bc.account.auth.application.dto.TokenReissueRequest;
import com.example.leaflog.bc.account.auth.application.dto.TokenResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final ReissueService reissueService;
    private final OauthLoginService oauthLoginService;


    @PostMapping("/reissue")
    public TokenResponseDto reissue(@RequestBody @Valid TokenReissueRequest request){
        return reissueService.reissue(request);
    }

    @GetMapping("/login/oauth2/code/github")
    public void oauthLogin(OAuth2UserRequest userRequest){
        oauthLoginService.login(userRequest);
    }
}
