package org.example.dto.response

//https://developers.google.com/workspace/sheets/api/reference/rest/v4/spreadsheets.values#ValueRange
data class SpreadSheetResponse(
    val spreadSheetId: String,
    val sheets: Sheets
)



data class Sheets(
    val range: String,
    val majorDimension: Dimension,
    val values: List<List<String>>
)

enum class Dimension(){
    DIMENSION_UNSPECIFIED,
    ROWS,
    COLUMNS
}


