
CREATE TABLE IF NOT EXISTS projectproduct (
    id uuid,
    mainproduct text,
    quantity int,
    units text,
    createdate timestamp,
    updatedate timestamp,
    projectid uuid,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS projectproduct_by_projectid (
projectid uuid,
id uuid,
PRIMARY KEY(projectid,id)
);
