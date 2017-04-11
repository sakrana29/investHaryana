
CREATE TABLE IF NOT EXISTS projectserviceformfielddata (
    id uuid,
    serviceid uuid,
    formfieldvalue text,
    projectid uuid,
    formfieldName text,
    serviceformfieldid uuid,
    formfieldtype text,
    formfieldOrder int,
    formtypeOption text,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS projectserviceformfielddata_by_project (
projectid uuid,
id uuid,
PRIMARY KEY(projectid, id)
);
