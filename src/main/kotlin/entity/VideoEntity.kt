package org.example.entity

import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
@Table(name = "videos")
class VideoEntity(

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_exercise_id", nullable = false)
    var sessionExercise: SessionExercisesEntity,

    @Column(name = "original_filename", nullable = false, columnDefinition = "text")
    var originalFilename: String,

    @Column(name = "content_type", nullable = false, columnDefinition = "text")
    var contentType: String,


    // Railway's actual S3 bucket name (BUCKET), not its display name.
    @Column(name = "storage_bucket", nullable = false, columnDefinition = "text")
    var storageBucket: String,

    // Stable object key
    @Column(name = "storage_key", nullable = false, columnDefinition = "text")
    var storageKey: String,

    // Expected file size while PENDING; verify against the object before READY.
    @Column(name = "byte_size", nullable = false)
    var byteSize: Long,

    @Column(name = "set_number")
    var setNumber: Int? = null,


    @Enumerated(EnumType.STRING)
    @Column(name = "upload_status", nullable = false, length = 16)
    var uploadStatus: VideoUploadStatus = VideoUploadStatus.PENDING,

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    var createdAt: OffsetDateTime? = null,

    // Set together with READY only after the backend verifies the stored object.
    @Column(name = "uploaded_at")
    var uploadedAt: OffsetDateTime? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
)
