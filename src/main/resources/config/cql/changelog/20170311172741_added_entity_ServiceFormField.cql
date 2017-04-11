
CREATE TABLE IF NOT EXISTS serviceFormField (
    id uuid,
    fieldName text,
    fieldType text,
    serviceID uuid,
    fieldTypeOption text,
    fieldRenderingOrder int,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS serviceFormField_by_serviceid (
serviceID uuid,
id uuid,
PRIMARY KEY(serviceID, id)
);
