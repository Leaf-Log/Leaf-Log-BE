package com.example.leaflog.bc.member.user.domain.repository;

import com.example.leaflog.bc.member.user.domain.User;
import com.example.leaflog.bc.member.user.domain.vo.GithubEmail;
import com.example.leaflog.bc.member.user.domain.vo.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//리포지토리와 도메인 모델은 거의 바뀌지 않음. 개발의 편의성과 실용성을 위해 설계
@Repository
public interface UserRepository extends JpaRepository<User, UserId> {
    Optional<User> findById(UserId id);

    Optional<User> findByGithubEmail(GithubEmail githubEmail);
}
