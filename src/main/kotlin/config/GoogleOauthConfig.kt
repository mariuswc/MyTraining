package org.example.config

import com.google.api.client.googleapis.apache.v2.GoogleApacheHttpTransport
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.json.gson.GsonFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GoogleOAuthConfig(
    @Value("\${google.oauth.client.id}")
    private val clientId: String,

    @Value("\${google.oauth.client.secret}")
    private val clientSecret: String,
) {
    @Bean
    fun googleAuthorizationCodeFlow(): GoogleAuthorizationCodeFlow {
        return GoogleAuthorizationCodeFlow.Builder(
            GoogleApacheHttpTransport.newTrustedTransport(),
            GsonFactory.getDefaultInstance(),
            clientId,
            clientSecret,
            listOf(
                "https://www.googleapis.com/auth/drive.metadata.readonly",
                "https://www.googleapis.com/auth/spreadsheets",
            ),
        )
            .setAccessType("offline")
            .build()
    }
}