
CREATE TABLE IF NOT EXISTS projectrawmaterial (
    id uuid,
    rawmaterial text,
    quantity int,
    units text,
    createdate timestamp,
    updatedate timestamp,
    projectid uuid,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS projectrawmaterial_by_projectid (
projectid uuid,
id uuid,
PRIMARY KEY(projectid,id)
);
