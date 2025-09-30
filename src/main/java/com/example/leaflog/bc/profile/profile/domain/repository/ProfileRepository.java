package com.example.leaflog.bc.profile.profile.domain.repository;

import com.example.leaflog.bc.profile.profile.domain.Profile;
import com.example.leaflog.bc.sharedkernel.user.vo.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UserId> {
}
