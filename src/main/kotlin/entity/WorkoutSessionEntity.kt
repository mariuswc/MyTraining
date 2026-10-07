package org.example.entity

import jakarta.persistence.*
import java.time.LocalDate
import java.time.OffsetDateTime

// One session for the day (date)
@Entity
@Table(name = "workout_sessions")
class WorkoutSessionEntity(

    @Column(name = "workout_date", nullable = false)
    var workoutDate: LocalDate,

    // Set by DB: DEFAULT CURRENT_TIMESTAMP
    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    var createdAt: OffsetDateTime? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
) {
    @OneToMany(mappedBy = "workoutSession")
    var sessionExercises: MutableList<SessionExercisesEntity> = mutableListOf()
}