package org.example.clients

import com.google.api.client.auth.oauth2.Credential
import org.example.dto.response.SpreadSheetResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Component
class GoogleSheetClient(
    @Qualifier("googleSheetConfig")
    val webClient: WebClient
) {
    fun getSheet(validToken: Credential,
                 spreadsheetId: String,
                 range: String)
    = webClient.get()
            .uri("/v4/spreadsheets/${spreadsheetId}/values/${range}")
            .headers{  headers -> headers.setBearerAuth(validToken.accessToken)}
            .retrieve()
            .bodyToMono<SpreadSheetResponse>()
            .onErrorMap { error ->
                RuntimeException("Could not retrieve spreadsheet", error)
            }}



