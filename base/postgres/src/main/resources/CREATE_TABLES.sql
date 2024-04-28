CREATE TABLE IF NOT EXISTS CONCEPTS (
    id SERIAL,
    uid VARCHAR,
    val VARCHAR,
    INDEX concepts_uid_idx (uid)
);

CREATE TABLE IF NOT EXISTS NODES (
    id SERIAL,
    uid VARCHAR,
    val VARCHAR,
    INDEX nodes_uid_idx (uid)
);