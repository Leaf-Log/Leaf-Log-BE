package com.example.leaflog.bc.profile.profile.application;

import com.example.leaflog.bc.account.user.domain.User;
import com.example.leaflog.bc.profile.profile.application.query.ProfileReadModel;
import com.example.leaflog.bc.profile.profile.domain.Profile;
import com.example.leaflog.bc.profile.profile.domain.repository.ProfileRepository;
import com.example.leaflog.bc.profile.profile.application.exception.ProfileNotFoundException;
import com.example.leaflog.bc.sharedkernel.user.provider.CurrentUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileQueryService {

    private final CurrentUserProvider currentUserProvider;
    private final ProfileRepository profileRepository;

    @Transactional(readOnly = true)
    public ProfileReadModel query(){
        User user = currentUserProvider.getCurrentUser();
        Profile profile = profileRepository.findById(user.userId())
                .orElseThrow(ProfileNotFoundException::new);

        return ProfileReadModel.from(user, profile);
    }
}
