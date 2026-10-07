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

    // BYTEA (do NOT use @Lob – that maps to OID on PostgreSQL)
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "video", nullable = false, columnDefinition = "bytea")
    var video: ByteArray,

    @Column(name = "set_number")
    var setNumber: Int? = null,

    // Set by DB: DEFAULT CURRENT_TIMESTAMP
    @Column(name = "uploaded_at", nullable = false, insertable = false, updatable = false)
    var uploadedAt: OffsetDateTime? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
)
