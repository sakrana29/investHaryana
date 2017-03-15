
CREATE TABLE IF NOT EXISTS projectrawmaterial (
    id uuid,
    projectid uuid,
    rawmaterial text,
    quantity int,
    units uuid,
    PRIMARY KEY(id)
);
