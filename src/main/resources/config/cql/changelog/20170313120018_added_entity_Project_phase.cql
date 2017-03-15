
CREATE TABLE IF NOT EXISTS project_phase (
    id uuid,
    projectid uuid,
    phase text,
    productcategory text,
    fci text,
    implementationdate timestamp,
    PRIMARY KEY(id)
);
