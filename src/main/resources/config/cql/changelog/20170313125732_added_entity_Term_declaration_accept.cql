
CREATE TABLE IF NOT EXISTS term_declaration_accept (
    id uuid,
    acceptance boolean,
    applicationdate timestamp,
    place text,
    createdate timestamp,
    updatedate timestamp,
    PRIMARY KEY(id)
);
