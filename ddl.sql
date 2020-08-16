CREATE TABLE IF NOT EXISTS member (
    member_no     INTEGER PRIMARY KEY AUTOINCREMENT,
    member_email  TEXT    UNIQUE,
    member_name   TEXT    NOT NULL,
    password      TEXT    NOT NULL
);