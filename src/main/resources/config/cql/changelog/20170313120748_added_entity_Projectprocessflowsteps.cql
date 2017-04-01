
CREATE TABLE IF NOT EXISTS projectprocessflowsteps (
    id uuid,
    steps text,
    createdate timestamp,
    updatedate timestamp,
    PRIMARY KEY(id)
);
