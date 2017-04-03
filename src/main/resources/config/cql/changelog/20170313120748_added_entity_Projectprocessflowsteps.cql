
CREATE TABLE IF NOT EXISTS projectprocessflowsteps (
    id uuid,
    steps text,
    createdate timestamp,
    updatedate timestamp,
    projectid uuid,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS projectprocessflowsteps_by_projectid (
projectid uuid,
id uuid,
PRIMARY KEY(projectid,id)
);
