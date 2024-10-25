package com.digital.silaai_integartion_service.clients.llm;

import com.digital.silaai_integartion_service.exceptions.BadRequestException;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class LlmService {

    @Value("${rest-api.ai-chat.generate-uri}")
    private URI aiChatGenerateUri;

    private final RestClient restClient;

    @Nonnull
    public Map<String, Object> getResponse(@Nonnull String userId, @Nonnull String request) {
        final var aiMessageRequest = LlmMessageRequest.builder()
                .userId(userId)
                .userInput(request)
                .build();
        ResponseEntity<HashMap> response = restClient.post()
                .uri(aiChatGenerateUri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(aiMessageRequest)
                .retrieve()
                .toEntity(HashMap.class);

        if (response.getStatusCode() != HttpStatusCode.valueOf(200)) {
            log.warn(response.toString());
            throw new BadRequestException(response.toString());
        }
        log.info(response.toString());

        return response.getBody();
    }

}
