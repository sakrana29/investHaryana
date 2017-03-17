
CREATE TABLE IF NOT EXISTS tehsil_subtehsil (
    id uuid,
    districtid uuid,
    tehsil_subtehsilname text,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS tehsil_by_district (
districtid uuid,
id uuid,
PRIMARY KEY(districtid,id)
);
