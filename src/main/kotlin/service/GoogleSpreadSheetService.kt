package org.example.service

import com.google.api.client.auth.oauth2.Credential
import org.example.clients.GoogleDriveClient
import org.example.clients.GoogleSheetClient
import org.example.dto.TrainingClass
import org.example.dto.response.GoogleDriveResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class GoogleSpreadSheetService(
    val googleDrive: GoogleDriveClient,
    val googleSpreadSheet: GoogleSheetClient
) {
    private val logger = LoggerFactory.getLogger(GoogleSpreadSheetService::class.java)

    fun getSpreadSheet(
        credential: Credential,
        id: String,
        week: String,
        trainingBlock: String
    ): Mono<List<TrainingClass>> {

        val sheetName = trainingBlock.replace("'", "''")

        val range = when (week) {

            "1" -> "'$sheetName'!A20:G83"
            "2" -> "'$sheetName'!I20:O83"
            "3" -> "'$sheetName'!Q20:W83"
            "4" -> "'$sheetName'!Y20:AE83"

            else -> throw IllegalArgumentException("Week must be between 1 and 4")
        }
        return googleSpreadSheet.getSheet(credential, id, range)
            .map { response ->
                val exercises = response.values.mapNotNull { row ->
                    val exerciseName = row.getOrNull(0)
                        ?.takeUnless { it.isBlank() }
                        ?: return@mapNotNull null

                    TrainingClass(
                        exerciseName = exerciseName,
                        sets = row.getOrNull(1),
                        reps = row.getOrNull(2),
                        weight = row.getOrNull(3),
                        rpe = row.getOrNull(4)
                    )
                }
                if (exercises.isEmpty()) {

                    val returnedRows = response.values
                        .filter { row -> row.any { it.isNotBlank() } }
                        .joinToString(separator = " | ") { row -> row.joinToString(prefix = "[", postfix = "]") }

                    logger.warn(
                        "No exercises mapped: requestedRange={}, returnedRange={}, dimension={}, rows={}, nonBlankRows={}, returnedRows={}",
                        range,
                        response.range,
                        response.majorDimension,
                        response.values.size,
                        response.values.count { row -> row.any { it.isNotBlank() } },
                        returnedRows
                    )
                }
                exercises
            }
    }

    fun listSheetsFromDrive(credential: Credential): Mono<GoogleDriveResponse> =
        googleDrive.getSheets(credential)
    }
