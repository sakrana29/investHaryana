
CREATE TABLE IF NOT EXISTS projectprocessflowsteps (
    id uuid,
    projectid uuid,
    steps text,
    PRIMARY KEY(id)
);
