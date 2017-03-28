
CREATE TABLE IF NOT EXISTS emissiondetail (
    id uuid,
    projectid uuid,
    particulars uuid,
    capacity text,
    type_of_fuel uuid,
    air_pollution_control_device uuid,
    projectname text,
    particularsname text,
    typeoffuelname text,
    airpollutioncontroldevicename text,
    PRIMARY KEY(id)
);
