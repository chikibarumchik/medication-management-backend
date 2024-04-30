CREATE TABLE medications
(
    id              BIGSERIAL PRIMARY KEY,
    user_id         BIGINT       NOT NULL,
    medication_name VARCHAR(100) not null,
    dosage          NUMERIC      NOT NULL,
    unit            VARCHAR(20)  NOT NULL,
    frequency       VARCHAR(100) NOT NULL,
    start_date      DATE         NOT NULL,
    end_date        DATE,
    notes           VARCHAR(1000),
    is_deleted      BOOLEAN                  DEFAULT false,
    created_by      VARCHAR      NOT NULL    DEFAULT '',
    modified_by     VARCHAR      NOT NULL    DEFAULT '',
    created_at      TIMESTAMP WITH TIME ZONE DEFAULT now() NOT NULL,
    modified_at     TIMESTAMP WITH TIME ZONE DEFAULT now() NOT NULL
);