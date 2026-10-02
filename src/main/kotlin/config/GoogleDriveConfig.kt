package org.example.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class GoogleDriveConfig {
    @Bean
    fun googleDriveWebClient(builder: WebClient.Builder) =
        builder
            .baseUrl("https://www.googleapis.com")
            .build()
}

