package org.example.clients

import org.example.dto.response.SpreadSheetResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient

    class GoogleDriveClient(
        @Qualifier("googleDriveConfig")
        val webClient: WebClient
    ) {
        @Bean
        fun getAllSpreadSheetsFromUser() =
            webClient.get()
                .uri { uriBuilder -> uriBuilder
                    .path("?q=mimeType='application/vnd.google-apps.spreadsheet")
                    .queryParam("includeItemsFromAllDrives", true)
                    .build()
                }
                .retrieve()
                .bodyToMono(SpreadSheetResponse::class.java)
                .onErrorMap { error -> throw error }

    }


