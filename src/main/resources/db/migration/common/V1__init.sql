CREATE TABLE IF NOT EXISTS CARD
(
    id          BIGSERIAL PRIMARY KEY,
    number      VARCHAR(143)         DEFAULT gen_random_uuid(),
    customer_id BIGINT      NULL,
    status      VARCHAR(15) NOT NULL DEFAULT 'CREATED',
    created_at  TIMESTAMP   NOT NULL DEFAULT NOW(),
    released_at TIMESTAMP   NULL     DEFAULT NULL,
    blocked_at  TIMESTAMP   NULL     DEFAULT NULL
);
