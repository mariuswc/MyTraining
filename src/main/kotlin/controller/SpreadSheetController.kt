package org.example.controller

import com.google.api.client.auth.oauth2.Credential
import jakarta.servlet.http.HttpSession
import org.example.dto.TrainingClass
import org.example.dto.response.GoogleDriveResponse
import org.springframework.http.HttpStatus
import org.example.service.GoogleSpreadSheetService
import org.springframework.web.bind.annotation.*
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
    @RequestParam trainingBlock: String,
    @RequestParam week: String)
    : Mono<List<TrainingClass>> {

        val credential = request.getAttribute("googleCredential") as Credential?
            ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Please sign in with google first")

        return spreadSheet.getSpreadSheet(credential, spreadsheetId, week, trainingBlock)
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
