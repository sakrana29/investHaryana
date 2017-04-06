
CREATE TABLE IF NOT EXISTS projectservicedetail (
    id uuid,
    projectid uuid,
    serviceid uuid,
    departmentname text,
    servicename text,
    isrequired boolean,
    markrequiredondate timestamp,
    markrequiredby text,
    isassigned boolean,
    markassignedby text,
    feerequired int,
    status text,
    comment text,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS projectservicedetail_by_project (
projectid uuid,
id uuid,
PRIMARY KEY(projectid, id)
);
