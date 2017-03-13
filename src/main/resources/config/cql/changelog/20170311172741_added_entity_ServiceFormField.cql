
CREATE TABLE IF NOT EXISTS serviceFormField (
    id uuid,
    fieldName text,
    fieldType text,
    serviceID uuid,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS serviceFormField_by_service (
serviceID uuid,
id uuid,
PRIMARY KEY(serviceID, id)
);

