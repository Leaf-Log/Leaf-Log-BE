package com.example.leaflog.bc.profile.profile.application.service.event;

import com.example.leaflog.bc.profile.profile.domain.Profile;
import com.example.leaflog.bc.profile.profile.domain.repository.ProfileRepository;
import com.example.leaflog.bc.profile.profile.domain.vo.Introduction;
import com.example.leaflog.bc.sharedkernel.user.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class ProfileCreationHandler {

    private final ProfileRepository profileRepository;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void on(UserCreatedEvent event){
        Introduction introduction = Introduction.of("");
        profileRepository.save(new Profile(event.userId(), event.userName(), introduction));
    }
}

//AFTER_COMMIT일 때는 User가 저장된 후 트랜잭션이 이미 끝났기 때문에 EntityManager가 닫혀 있음
//이러한 이유로 저장이 안됐던거임