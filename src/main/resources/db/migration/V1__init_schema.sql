CREATE TABLE exercises (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name TEXT NOT NULL UNIQUE CHECK (btrim(name) <> '')
);


CREATE TABLE workout_sessions (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    workout_date DATE NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE session_exercises (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    workout_session_id BIGINT NOT NULL REFERENCES workout_sessions(id) ON DELETE RESTRICT,
    exercise_id BIGINT NOT NULL REFERENCES exercises(id) ON DELETE RESTRICT,
    CONSTRAINT uq_session_exercises_session_exercise UNIQUE (workout_session_id, exercise_id)
);


CREATE TABLE videos (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    session_exercise_id BIGINT NOT NULL REFERENCES session_exercises(id) ON DELETE RESTRICT,
    set_number INTEGER CHECK (set_number > 0),
    original_filename TEXT NOT NULL CHECK (btrim(original_filename) <> ''),
    content_type TEXT NOT NULL CHECK (btrim(content_type) <> ''),
    video BYTEA NOT NULL CHECK (octet_length(video) > 0),
    uploaded_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_workout_sessions_date ON workout_sessions(workout_date);
CREATE INDEX idx_session_exercises_exercise_session
    ON session_exercises(exercise_id, workout_session_id);
CREATE INDEX idx_videos_session_exercise_set
    ON videos(session_exercise_id, set_number);
