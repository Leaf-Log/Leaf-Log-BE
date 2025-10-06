package com.example.leaflog.bc.account.auth.application.port.in;

import com.example.leaflog.bc.account.auth.application.dto.TokenReissueRequest;
import com.example.leaflog.bc.account.auth.application.dto.TokenResponse;

public interface ReissueUseCase {
    TokenResponse reissue(TokenReissueRequest request);
}
