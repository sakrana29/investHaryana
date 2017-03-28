
CREATE TABLE IF NOT EXISTS projectservicedetail (
    id uuid,
    projectid uuid,
    serviceid uuid,
    userlogin text,
    servicerequired boolean,
    servicestatus text,
    assigndate timestamp,
    servicefee double,
    remarks text,
    projectname text,
    servicename text,
    PRIMARY KEY(id)
);
