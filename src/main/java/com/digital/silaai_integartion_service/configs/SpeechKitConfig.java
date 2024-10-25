package com.digital.silaai_integartion_service.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Getter
@Configuration
public class SpeechKitConfig {

    private final String standardUri = "https://stt.api.cloud.yandex.net/speech/v1/stt:recognize";

    @Value("${rest-api.speech-kit.api-key}")
    private String apiKey;

    private final String lang = "ru-RU";

    private final String topic = "general";

    private final Boolean profanityFilter = false;

    private final Boolean rawResults = false;

    private final String format = "oggopus";

    private final String sampleRateHertz = "48000";

    private final String folderId = "";

    private final URI clientUrl = URI.create(
            standardUri + "?topic=" + topic + "&lang=" + lang + "&format=" + format + "&=folderId=" + folderId
    );

}
