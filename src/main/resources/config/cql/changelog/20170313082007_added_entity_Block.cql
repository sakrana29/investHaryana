
CREATE TABLE IF NOT EXISTS block (
    id uuid,
    districtid uuid,
    blockname text,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS block_by_district (
districtid uuid,
id uuid,
PRIMARY KEY(districtid,id)
);
