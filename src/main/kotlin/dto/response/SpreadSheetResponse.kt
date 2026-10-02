package org.example.dto.response

import java.util.Collections.emptyList

//https://developers.google.com/workspace/sheets/api/reference/rest/v4/spreadsheets.values#ValueRange

data class SpreadSheetResponse(
    val range: String,
    val majorDimension: Dimension,
    val values: List<List<String>> = emptyList()
)

enum class Dimension(){
    DIMENSION_UNSPECIFIED,
    ROWS,
    COLUMNS
}


