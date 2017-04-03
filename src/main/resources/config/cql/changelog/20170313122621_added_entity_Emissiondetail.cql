
CREATE TABLE IF NOT EXISTS emissiondetail (
    id uuid,
    capacity text,
    particulars text,
    type_of_fuel text,
    air_pollution_control_device text,
    createdate timestamp,
    updatedate timestamp,
    projectid uuid,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS emissiondetail_by_projectid (
projectid uuid,
id uuid,
PRIMARY KEY(projectid,id)
);
