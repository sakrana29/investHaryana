
CREATE TABLE IF NOT EXISTS projectprocessflowsteps (
    id uuid,
    projectid uuid,
    steps text,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS projectprocessflowsteps_By_projectid (
id uuid,
projectid uuid,
PRIMARY KEY(projectid,id)
);
