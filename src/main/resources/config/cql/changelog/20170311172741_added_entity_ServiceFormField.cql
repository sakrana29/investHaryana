
CREATE TABLE IF NOT EXISTS serviceFormField (
    id uuid,
    fieldName text,
    fieldType text,
    serviceID uuid,
    fieldTypeOption text,
    fieldRenderingOrder int,
    PRIMARY KEY(id)
);
