package org.example.dto.response

data class GoogleDriveResponse(
    val files: List<File>,
    val nextPageToken: String?,
    val kind: String,
    val incompleteSearch: Boolean
)

data class File(
    val kind: String,
    val driveId: String? = null,
    val id: String,
    val name: String
)