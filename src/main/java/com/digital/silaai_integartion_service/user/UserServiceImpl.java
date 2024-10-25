package com.digital.silaai_integartion_service.user;

import com.fasterxml.uuid.impl.TimeBasedEpochRandomGenerator;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final TimeBasedEpochRandomGenerator timeBasedEpochUUIDGenerator;

    @Nonnull
    @Override
    public GetUserUUIDResponse createUserUUID() {
        return GetUserUUIDResponse.builder()
                                  .id(timeBasedEpochUUIDGenerator.generate())
                                  .build();
    }
}
