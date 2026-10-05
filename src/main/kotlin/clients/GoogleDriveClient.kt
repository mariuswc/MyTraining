package org.example.clients

import com.google.api.client.auth.oauth2.Credential
import org.example.dto.response.GoogleDriveResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@Component
class GoogleDriveClient(
        @Qualifier("googleDriveWebClient")
        val webClient: WebClient
    ) {
        fun getSheets(credential: Credential): Mono<GoogleDriveResponse> =
            webClient.get()
                .uri { uriBuilder -> uriBuilder
                    .path("/drive/v3/files")
                    .queryParam("q","mimeType = 'application/vnd.google-apps.spreadsheet'")
                    .queryParam("supportAllDrives", true)
                    .build()
                }
                .headers{  headers -> headers.setBearerAuth(credential.accessToken)}
                .retrieve()
                .bodyToMono<GoogleDriveResponse>()
                .onErrorMap { error -> throw RuntimeException("Could not retrieve the users drive", error) }
    }


