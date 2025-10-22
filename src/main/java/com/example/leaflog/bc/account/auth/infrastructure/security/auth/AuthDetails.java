package com.example.leaflog.bc.account.auth.infrastructure.security.auth;

import com.example.leaflog.bc.account.user.domain.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class AuthDetails implements OAuth2User, UserDetails {

    private final User user;
    private final Map<String, Object> attributes;
    private final String githubAccessToken;

    public AuthDetails(User user) {
        this.user = user;
        this.githubAccessToken = "";
        this.attributes = new HashMap<>();
    }

    public AuthDetails(User user, String githubAccessToken) {
        this.user = user;
        this.githubAccessToken = githubAccessToken;
        this.attributes = new HashMap<>();
    }

    public AuthDetails(User user, Map<String, Object> attributes, String githubAccessToken) {
        this.user = user;
        this.attributes = attributes != null ? new HashMap<>(attributes) : new HashMap<>();
        this.githubAccessToken = githubAccessToken;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return null; //우리 서비스는 소셜(깃허브) 로그인만
    }

    @Override
    public String getUsername() {
        return user.getPrincipalEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return user.getPrincipalEmail();
    }
}

