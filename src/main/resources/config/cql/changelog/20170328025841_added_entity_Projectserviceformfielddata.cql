
CREATE TABLE IF NOT EXISTS projectserviceformfielddata (
    id uuid,
    serviceid uuid,
    formfieldvalue text,
    projectid uuid,
    formfieldName text,
    PRIMARY KEY(id)
);
