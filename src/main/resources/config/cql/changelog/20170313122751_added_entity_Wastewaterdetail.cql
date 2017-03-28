
CREATE TABLE IF NOT EXISTS wastewaterdetail (
    id uuid,
    projectid uuid,
    source_of_generation text,
    quantity int,
    description text,
    naturetype text,
    mode_of_disposal text,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS wastewaterdetail_By_Projectid (
id uuid,
projectid uuid,
PRIMARY KEY(projectid,id)
);
