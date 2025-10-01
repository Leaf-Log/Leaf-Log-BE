package com.example.leaflog.bc.sharedkernel.user.provider;

import com.example.leaflog.bc.account.user.domain.User;
import com.example.leaflog.bc.account.user.domain.repository.UserRepository;
import com.example.leaflog.bc.account.user.domain.vo.GithubEmail;
import com.example.leaflog.bc.account.auth.application.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUserProvider {
    private final UserRepository userRepository;

    public User getCurrentUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByGithubEmail(GithubEmail.of(email))
                .orElseThrow(UserNotFoundException::new);
    }
}
