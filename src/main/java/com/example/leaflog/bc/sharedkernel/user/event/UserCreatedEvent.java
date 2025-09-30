package com.example.leaflog.bc.sharedkernel.user.event;

import com.example.leaflog.bc.sharedkernel.user.vo.UserId;
import com.example.leaflog.bc.sharedkernel.user.vo.UserName;

public record UserCreatedEvent(
        UserId userId,
        UserName userName
) {
}
