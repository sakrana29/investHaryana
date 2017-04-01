
CREATE TABLE IF NOT EXISTS projectrawmaterial (
    id uuid,
    rawmaterial text,
    quantity int,
    units text,
    createdate timestamp,
    updatedate timestamp,
    PRIMARY KEY(id)
);
