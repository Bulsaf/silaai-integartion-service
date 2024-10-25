package com.digital.silaai_integartion_service.clients.speechkit;

import com.digital.silaai_integartion_service.configs.SpeechKitConfig;
import com.digital.silaai_integartion_service.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpeechKitServiceImpl implements SpeechKitService {

    private final SpeechKitConfig speechKitConfig;

    public SpeechKitResponse getResponse(byte[] bytes) {
        var response = RestClient.create()
                .post()
                .uri(speechKitConfig.getClientUrl())
                .header("Authorization", "Api-Key " + speechKitConfig.getApiKey())
                .body(bytes)
                .retrieve()
                .toEntity(SpeechKitResponse.class);

        if (response.getStatusCode() != HttpStatusCode.valueOf(200) || response.getBody() == null){
            log.warn(response.toString());
            throw new BadRequestException("Invalid response code: " + response.getStatusCode());
        }
        log.info(response.toString());

        return response.getBody();
    }
}
