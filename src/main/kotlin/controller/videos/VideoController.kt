package org.example.controller.videos

import org.example.dto.request.StartVideoUploadRequest
import org.example.dto.response.VideoPlaybackResponse
import org.example.dto.response.VideoResponse
import org.example.dto.response.VideoUploadResponse
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

@RestController
@RequestMapping("/videos")
class VideoController {

    // Receives metadata. The frontend uploads the file to the returned URL with PUT.
    @PostMapping("/uploads")
    @ResponseStatus(HttpStatus.CREATED)
    fun startUpload(@RequestBody request: StartVideoUploadRequest): VideoUploadResponse {
        // TODO: return videoService.startUpload(request)
        // Validate metadata, resolve/create sessionExercise, save PENDING and sign a URL.
        return notImplemented("startUpload")
    }

    // Called after the frontend finishes its PUT to Railway.
    @PostMapping("/{videoId}/complete")
    fun completeUpload(@PathVariable("videoId") videoId: Long): VideoResponse {
        // TODO: return videoService.completeUpload(videoId)
        // Verify the stored object and its size before setting READY and uploadedAt.
        return notImplemented("completeUpload")
    }

    @GetMapping
    fun getVideos(
        @RequestParam("date", required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate?,
        @RequestParam("exercise", required = false) exercise: String?,
        @RequestParam("set", required = false) setNumber: Int?,
        @RequestParam("sessionId", required = false) sessionId: Long?,
        @RequestParam("page", defaultValue = "0") page: Int,
        @RequestParam("size", defaultValue = "20") size: Int,
    ): List<VideoResponse> {
        // TODO: return videoService.getVideos(date, exercise, setNumber, sessionId, page, size)
        // Validate positive set/IDs, page >= 0 and size in 1..100; return newest first.
        return notImplemented("getVideos")
    }

    // Returns metadata
    @GetMapping("/{videoId}")
    fun getVideo(@PathVariable("videoId") videoId: Long): VideoResponse {
        // TODO: return videoService.getVideo(videoId) -- 404 if not found.
        return notImplemented("getVideo")
    }

    // Returns a short-lived playback URL for a READY video.
    @GetMapping("/{videoId}/playback-url")
    fun getPlaybackUrl(@PathVariable("videoId") videoId: Long): VideoPlaybackResponse {
        // TODO: return videoService.getPlaybackUrl(videoId)
        // Generate a fresh signed GET URL; do not store the URL in the database.
        return notImplemented("getPlaybackUrl")
    }

    @DeleteMapping("/{videoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteVideo(@PathVariable("videoId") videoId: Long) {
        // TODO: videoService.deleteVideo(videoId)
        // Delete both the Railway object and database metadata, allowing retries.
        notImplemented("deleteVideo")
    }

    private fun notImplemented(operation: String): Nothing =
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "$operation is not implemented yet")
}
