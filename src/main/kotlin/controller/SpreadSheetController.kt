package org.example.controller

import com.google.api.client.auth.oauth2.Credential
import jakarta.servlet.http.HttpSession
import org.example.dto.response.GoogleDriveResponse
import org.example.dto.response.SpreadSheetResponse
import org.example.service.GoogleSpreadSheetService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

@RestController
class SpreadSheetController(
    private val spreadSheet: GoogleSpreadSheetService
) {

@GetMapping("/spreadsheets/{spreadsheetId}")
    fun getSpreadSheet(
    request: HttpSession,
    @PathVariable spreadsheetId: String,
    @RequestParam("sheetName", defaultValue = "Blokk oversikt") sheetName: String,)
    : Mono<SpreadSheetResponse> {

        val credential = request.getAttribute("googleCredential") as Credential?
            ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Please sign in with google first")

        return spreadSheet.getSpreadSheet(credential, spreadsheetId, sheetName)
    }

@GetMapping("/spreadsheets")
    fun listAllSpreadSheetFromDrive(request: HttpSession): Mono<GoogleDriveResponse>{

    val credential = request.getAttribute("googleCredential") as Credential?
        ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Please sign in with google first")

        return spreadSheet.listSheetsFromDrive(credential)


    }

    fun deleteSpreadSheet(){

    }
}