package org.example.clients

import org.example.dto.response.SpreadSheetResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono


class GoogleSheetClient(
    @Qualifier("googleSheetConfig")
    val webClient: WebClient
) {
    fun getSpreadSheet(spreadsheetId: String, range: String) =
        webClient.get()
            .uri("/v4/spreadsheets/${spreadsheetId}/values/${range}")
            .retrieve()
            .bodyToMono<SpreadSheetResponse>()
            .onErrorMap { error ->
                RuntimeException("Could not retrieve spreadsheet", error)
            }}



