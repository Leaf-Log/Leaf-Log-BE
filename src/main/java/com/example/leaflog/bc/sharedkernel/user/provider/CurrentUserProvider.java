package com.example.leaflog.bc.sharedkernel.user.provider;

import com.example.leaflog.bc.member.user.domain.User;
import com.example.leaflog.bc.member.user.domain.repository.UserRepository;
import com.example.leaflog.bc.member.user.domain.vo.GithubEmail;
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
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
    }
}
