
CREATE TABLE IF NOT EXISTS departmentService (
    id uuid,
    serviceName text,
    serviceDescription text,
    duration int,
    stage text,
    departmentid uuid,
    PRIMARY KEY(id)
);
