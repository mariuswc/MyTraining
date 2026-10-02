package org.example.clients

import org.example.dto.response.SpreadSheetResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

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
                .header("TODO")
                .retrieve()
                .bodyToMono<SpreadSheetResponse>()
                .onErrorMap { error -> throw RuntimeException("Could not retrieve the users drive", error) }
    }


