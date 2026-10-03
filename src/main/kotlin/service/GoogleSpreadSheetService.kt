package org.example.service

import com.google.api.client.auth.oauth2.Credential
import org.example.clients.GoogleDriveClient
import org.example.clients.GoogleSheetClient
import org.example.dto.response.GoogleDriveResponse
import org.example.dto.response.SpreadSheetResponse
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class GoogleSpreadSheetService(
    val googleDrive: GoogleDriveClient,
    val googleSpreadSheet: GoogleSheetClient
) {

    fun getSpreadSheet(credential: Credential, id: String, sheetName: String): Mono<SpreadSheetResponse> {

        val range = "'${sheetName}'!A19:AE83"

        return googleSpreadSheet.getSheet(credential,id, range)

    }


    fun listSheetsFromDrive(credential: Credential): Mono<GoogleDriveResponse> =
        googleDrive.getSheets(credential)

}