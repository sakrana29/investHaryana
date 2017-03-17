
CREATE TABLE IF NOT EXISTS district (
    id uuid,
    stateid uuid,
    districtname text,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS district_by_state (
stateid uuid,
id uuid,
PRIMARY KEY(stateid,id)
);
