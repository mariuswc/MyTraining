package org.example.entity

import jakarta.persistence.*

@Entity
@Table(name = "exercises")
class ExerciseEntity(

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "text")
    var name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
)
