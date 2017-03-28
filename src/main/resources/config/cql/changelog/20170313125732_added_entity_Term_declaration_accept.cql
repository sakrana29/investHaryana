
CREATE TABLE IF NOT EXISTS term_declaration_accept (
    id uuid,
    projectid uuid,
    acceptance boolean,
    applicationdate timestamp,
    place text,
    signature blob,
    signature_content_type text,
    projectname text,
    PRIMARY KEY(id)
);
