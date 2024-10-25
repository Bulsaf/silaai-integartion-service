package com.digital.silaai_integartion_service.security;

import com.digital.silaai_integartion_service.exceptions.NotFoundException;
import com.digital.silaai_integartion_service.storage.entities.UserEntity;
import com.digital.silaai_integartion_service.storage.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                                              .orElseThrow(() -> new NotFoundException("User is not found"));
        return UserDetailsImpl.build(userEntity);
    }
}
