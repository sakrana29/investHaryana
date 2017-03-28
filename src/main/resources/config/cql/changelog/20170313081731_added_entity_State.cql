
CREATE TABLE IF NOT EXISTS state (
    id uuid,
    countryid uuid,
    statename text,
    countryname text,
    PRIMARY KEY(id)
);


CREATE TABLE IF NOT EXISTS state_by_country (
countryid uuid,
id uuid,
PRIMARY KEY(countryid,id)
);
