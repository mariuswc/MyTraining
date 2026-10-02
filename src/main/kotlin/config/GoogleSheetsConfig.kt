package org.example.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient


@Configuration
class GoogleSheetsConfig {

    @Bean
    fun googleSheetConfig(builder: WebClient.Builder) =
        builder
            .baseUrl("https://sheets.googleapis.com")
            .build()
}