
CREATE TABLE IF NOT EXISTS departmentService (
    id uuid,
    serviceName text,
    serviceDescription text,
    departmentID uuid,
    duration int,
    stage text,
    PRIMARY KEY(id)
);
