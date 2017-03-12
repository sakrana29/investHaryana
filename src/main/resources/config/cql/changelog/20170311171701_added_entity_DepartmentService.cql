
CREATE TABLE IF NOT EXISTS departmentService (
    id uuid,
    serviceName text,
    serviceDescription text,
    departmentID uuid,
    PRIMARY KEY(id)
);
