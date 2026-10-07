package org.example.entity

import jakarta.persistence.*

// One specific exercise in a session
@Entity
@Table(
    name = "session_exercises",
    uniqueConstraints = [
        UniqueConstraint(
            name = "uq_session_exercises_session_exercise",
            columnNames = ["workout_session_id", "exercise_id"],
        )
    ],
)
class SessionExercisesEntity(

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workout_session_id", nullable = false)
    var workoutSession: WorkoutSessionEntity,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    var exercise: ExerciseEntity,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
) {
    @OneToMany(mappedBy = "sessionExercise")
    var videos: MutableList<VideoEntity> = mutableListOf()
}