package org.example.clients

import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient

class GoogleSheetClient(
    val webClient: WebClient
) {
    @Bean
    fun googleSheetClient() = webClient






}