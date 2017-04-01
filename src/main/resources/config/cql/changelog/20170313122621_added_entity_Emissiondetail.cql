
CREATE TABLE IF NOT EXISTS emissiondetail (
    id uuid,
    capacity text,
    particulars text,
    type_of_fuel text,
    air_pollution_control_device text,
    createdate timestamp,
    updatedate timestamp,
    PRIMARY KEY(id)
);
