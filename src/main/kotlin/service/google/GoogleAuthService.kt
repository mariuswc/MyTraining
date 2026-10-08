package org.example.service.google

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class GoogleAuthService(
    private val flow: GoogleAuthorizationCodeFlow,
    @Value("\${google.oauth.redirect.uri}")
    private val redirectUri: String,
) {
    fun createAuthorizationUrl(state: String): String {
        return flow.newAuthorizationUrl()
            .setRedirectUri(redirectUri)
            .setState(state)
            .build()
    }

    fun exchangeCodeForCredential(code: String): Credential {
        val tokens = flow.newTokenRequest(code)
            .setRedirectUri(redirectUri)
            .execute()

        return flow.createAndStoreCredential(tokens, null)
}
}