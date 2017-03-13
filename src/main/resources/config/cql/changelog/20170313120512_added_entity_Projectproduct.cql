
CREATE TABLE IF NOT EXISTS projectproduct (
    id uuid,
    projectid uuid,
    mainproduct text,
    quantity int,
    units uuid,
    PRIMARY KEY(id)
);
