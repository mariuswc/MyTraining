package org.example.controller

import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpSession
import org.example.service.GoogleAuthService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Controller
class GoogleAuthController(
    private val authService: GoogleAuthService,
) {
    @GetMapping("/auth/google")
    fun connectToGoogle(session: HttpSession, response: HttpServletResponse) {
        val state = UUID.randomUUID().toString()
        session.setAttribute("googleState", state)

        val googleUrl = authService.createAuthorizationUrl(state)

        response.sendRedirect(googleUrl)
    }

    @GetMapping("/auth/google/callback")
    @ResponseBody
    fun callback(
        @RequestParam(required = false) code: String?,
        @RequestParam(required = false) state: String?,
        session: HttpSession,
    ): String {
        val expectedState = session.getAttribute("googleState") as? String
        session.removeAttribute("googleState")

        if (expectedState == null || state != expectedState || code == null) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Bad Request or an aborted session",
            )
        }

        val credential = authService.exchangeCodeForCredential(code)
        session.setAttribute("googleCredential", credential)

        return "Authorized with Google"
    }
}