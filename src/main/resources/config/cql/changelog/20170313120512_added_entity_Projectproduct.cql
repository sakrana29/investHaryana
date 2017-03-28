
CREATE TABLE IF NOT EXISTS projectproduct (
    id uuid,
    projectid uuid,
    mainproduct text,
    quantity int,
    units text,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS projectproduct_by_projectid (
id uuid,
projectid uuid,
PRIMARY KEY(projectid,id)
);
