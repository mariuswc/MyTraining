package org.example.dto.response

import org.apache.logging.log4j.util.StringMap

data class GoogleDriveResponse(
    val files: File,
    val nextPageToken: String,
    val kind: String,
    val incompleteSearch: Boolean
)

data class File(
    val kind: String,
    val driveId: String,
)