package com.example.leaflog.bc.profile.profile.application.service;

import com.example.leaflog.bc.account.user.domain.User;
import com.example.leaflog.bc.profile.profile.application.port.in.ProfileQueryUseCase;
import com.example.leaflog.bc.profile.profile.application.dto.ProfileReadModel;
import com.example.leaflog.bc.profile.profile.domain.Profile;
import com.example.leaflog.bc.profile.profile.domain.repository.ProfileRepository;
import com.example.leaflog.bc.profile.profile.application.service.exception.ProfileNotFoundException;
import com.example.leaflog.bc.sharedkernel.user.provider.CurrentUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileQueryService implements ProfileQueryUseCase {

    private final CurrentUserProvider currentUserProvider;
    private final ProfileRepository profileRepository;

    @Override
    @Transactional(readOnly = true)
    public ProfileReadModel query(){
        User user = currentUserProvider.getCurrentUser();
        Profile profile = profileRepository.findById(user.userId())
                .orElseThrow(ProfileNotFoundException::new);

        return ProfileReadModel.from(user, profile);
    }
}
