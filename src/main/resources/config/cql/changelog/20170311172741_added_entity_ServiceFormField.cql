
CREATE TABLE IF NOT EXISTS serviceFormField (
    id uuid,
    fieldName text,
    fieldType text,
    serviceID uuid,
    PRIMARY KEY(id)
);
