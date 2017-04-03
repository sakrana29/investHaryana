
CREATE TABLE IF NOT EXISTS wastewaterdetail (
    id uuid,
    source_of_generation text,
    quantity int,
    naturetype text,
    mode_of_disposal text,
    createdate timestamp,
    updatedate timestamp,
    projectid uuid,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS wastewaterdetail_by_projectid (
projectid uuid,
id uuid,
PRIMARY KEY(projectid,id)
);
