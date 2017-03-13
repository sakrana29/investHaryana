
CREATE TABLE IF NOT EXISTS departmentService (
    id uuid,
    serviceName text,
    serviceDescription text,
    departmentID uuid,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS departmentService_by_department (
    departmentID uuid,
    id uuid,
    PRIMARY KEY(departmentID, id)
);
