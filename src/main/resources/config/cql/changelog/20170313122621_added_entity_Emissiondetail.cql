
CREATE TABLE IF NOT EXISTS emissiondetail (
    id uuid,
    projectid uuid,
    capacity text,
    particulars text,
    type_of_fuel text,
    air_pollution_control_device text,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS emissiondetail_By_projectid (
id uuid,
projectid uuid,
PRIMARY KEY(projectid,id)
);
