package com.digital.silaai_integartion_service.configs;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochRandomGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UUIDConfig {

    @Bean
    public TimeBasedEpochRandomGenerator timeBasedEpochUUIDGenerator(){
        return Generators.timeBasedEpochRandomGenerator();
    }

}
