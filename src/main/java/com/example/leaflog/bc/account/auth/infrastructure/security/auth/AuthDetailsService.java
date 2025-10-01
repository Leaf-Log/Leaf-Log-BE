package com.example.leaflog.bc.account.auth.infrastructure.security.auth;

import com.example.leaflog.bc.account.user.domain.repository.UserRepository;
import com.example.leaflog.bc.account.user.domain.vo.GithubEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByGithubEmail(GithubEmail.of(email))
                .map(AuthDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
