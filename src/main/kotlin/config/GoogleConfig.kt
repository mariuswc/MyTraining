package org.example.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class GoogleConfig {

    @Bean
    fun googleSheetConfig(builder: WebClient.Builder) =
        builder
            .baseUrl("https://sheets.googleapis.com")
            .defaultHeader("Authorization", "Bearer $apiKey")
            .build()

    fun googleDriveConfig(builder: WebClient.Builder) =
        builder
            .baseUrl("https://www.googleapis.com/drive/v3/files")
            .defaultHeader("Authorization", "Bearer $apiKey")
            .build()
}