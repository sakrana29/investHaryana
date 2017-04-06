
CREATE TABLE IF NOT EXISTS serviceFormField (
    id uuid,
    fieldName text,
    fieldType text,
    serviceID uuid,
    option text,
    PRIMARY KEY(id)
);
