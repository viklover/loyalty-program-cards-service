CREATE TABLE IF NOT EXISTS CARD
(
    id          BIGSERIAL PRIMARY KEY,
    number      VARCHAR(143)       DEFAULT gen_random_uuid(),
    customer_id BIGINT    NULL,
    is_owned    BOOLEAN   NOT NULL DEFAULT FALSE,
    is_blocked  BOOLEAN   NOT NULL DEFAULT FALSE,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    released_at TIMESTAMP NULL     DEFAULT NULL,
    blocked_at  TIMESTAMP NULL     DEFAULT NULL
);
