
CREATE TABLE IF NOT EXISTS projectproduct (
    id uuid,
    projectid uuid,
    mainproduct text,
    quantity int,
    units uuid,
    projectname text,
    unitsname text,
    PRIMARY KEY(id)
);
