ALTER TABLE assignments ADD COLUMN school_id INTEGER NOT NULL DEFAULT 1;

ALTER TABLE assignments DROP CONSTRAINT no_duplicate_tag;

ALTER TABLE assignments ADD CONSTRAINT unique_assignment UNIQUE (topic_id, grade_id, subject_id, assignment_date, school_id)
