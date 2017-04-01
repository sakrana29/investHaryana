
CREATE TABLE IF NOT EXISTS projectproduct (
    id uuid,
    mainproduct text,
    quantity int,
    units text,
    createdate timestamp,
    updatedate timestamp,
    PRIMARY KEY(id)
);
