
CREATE TABLE IF NOT EXISTS environment_impactdetail (
    id uuid,
    projectid uuid,
    water_process int,
    water_cooling int,
    water_domestic int,
    water_other int,
    waste_water_process int,
    waste_water_cooling int,
    waste_water_domesting int,
    waste_water_other int,
    waste_water_treatment text,
    document_attached text,
    other text,
    source_of_water_supply text,
    mode_of_disposal_for_discharge text,
    emissionname text,
    wastewaterdetailid text,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS environment_impactdetail_By_Projectid (
id uuid,
projectid uuid,
PRIMARY KEY(projectid,id)
);
