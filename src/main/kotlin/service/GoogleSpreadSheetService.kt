package org.example.service

import com.google.api.client.auth.oauth2.Credential
import org.example.clients.GoogleDriveClient
import org.example.clients.GoogleSheetClient
import org.example.dto.response.GoogleDriveResponse
import org.example.dto.response.SpreadSheetResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.logging.Logger

@Service
class GoogleSpreadSheetService(
    val googleDrive: GoogleDriveClient,
    val googleSpreadSheet: GoogleSheetClient
) {

    fun getSpreadSheet(credential: Credential, id: String, range: String): Mono<SpreadSheetResponse> =
        googleSpreadSheet.getSheet(credential,id, range)


    fun listSheetsFromDrive(credential: Credential): Mono<GoogleDriveResponse> =
        googleDrive.getSheets(credential)

}