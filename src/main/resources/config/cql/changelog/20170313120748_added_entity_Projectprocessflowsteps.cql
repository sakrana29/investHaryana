
CREATE TABLE IF NOT EXISTS projectprocessflowsteps (
    id uuid,
    projectid uuid,
    steps text,
    projectname text,
    PRIMARY KEY(id)
);
