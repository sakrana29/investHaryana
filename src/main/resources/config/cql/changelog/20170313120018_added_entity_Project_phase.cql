
CREATE TABLE IF NOT EXISTS project_phase (
    id uuid,
    projectid uuid,
    phase text,
    productcategory text,
    fci text,
    implementationdate timestamp,
    createdate timestamp,
    updatedate timestamp,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS projectphase_by_projectid (
projectid uuid,
id uuid,
PRIMARY KEY(projectid,id)
);
