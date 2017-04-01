
CREATE TABLE IF NOT EXISTS projectserviceformfielddata (
    id uuid,
    serviceid uuid,
    formfieldvalue text,
    projectserviceformfieldvalue uuid,
    projectid uuid,
    PRIMARY KEY(id)
);
