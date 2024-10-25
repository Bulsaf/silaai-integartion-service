package com.digital.silaai_integartion_service.users;

import com.digital.silaai_integartion_service.exceptions.BadRequestException;
import com.digital.silaai_integartion_service.exceptions.NotFoundException;
import com.digital.silaai_integartion_service.storage.entities.UserEntity;
import com.digital.silaai_integartion_service.storage.repositories.UserRepository;
import com.digital.silaai_integartion_service.users.requests.NewUserRequest;
import com.digital.silaai_integartion_service.users.responses.GetUserResponse;
import com.digital.silaai_integartion_service.users.responses.NewUserResponse;
import com.fasterxml.uuid.impl.TimeBasedEpochRandomGenerator;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final TimeBasedEpochRandomGenerator timeBasedEpochUUIDRandomGenerator;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public NewUserResponse createUser(@Nonnull NewUserRequest user) {
        if (userRepository.existsByUsername(user.username())) {
            throw new BadRequestException("User with username: " + user.username() + " already exists");
        }

        UserEntity newUser = UserEntity.builder()
                                       .id(timeBasedEpochUUIDRandomGenerator.generate())
                                       .username(user.username())
                                       .password(passwordEncoder.encode(user.password()))
                                       .role("ROLE_USER")
                                       .build();
        UserEntity createdUser = userRepository.save(newUser);
        return NewUserResponse.builder()
                              .id(createdUser.getId())
                              .username(createdUser.getUsername())
                              .build();
    }

    @Override
    public GetUserResponse getUserByUsername(@Nonnull String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return GetUserResponse.builder()
                              .id(userEntity.getId())
                              .username(userEntity.getUsername())
                              .role(userEntity.getRole())
                              .build();
    }

    @Override
    public boolean checkUser(@Nonnull String username, @Nonnull String password) {
        UserEntity userRecord = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return passwordEncoder.matches(password, userRecord.getPassword());
    }

}
