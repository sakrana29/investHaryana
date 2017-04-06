
CREATE TABLE IF NOT EXISTS departmentService (
    id uuid,
    serviceName text,
    serviceDescription text,
    duration int,
    stage text,
    departmentname text,
    departmentid uuid,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS departmentService_by_department (
departmentid uuid,
id uuid,
PRIMARY KEY(departmentid, id)
);

