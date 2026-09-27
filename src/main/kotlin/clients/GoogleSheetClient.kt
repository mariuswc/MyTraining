package org.example.clients

import org.example.dto.response.SpreadSheetResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient


class GoogleSheetClient(
    @Qualifier("googleSheetConfig")
    val webClient: WebClient
) {
    fun getSpreadSheet(spreadsheetId: String, range: String) =
        webClient.get()
            .uri("/v4/spreadsheets/${spreadsheetId}/values/${range}")
            .retrieve()
            .bodyToMono(SpreadSheetResponse::class.java)
            .onErrorMap { error -> throw error }
}

