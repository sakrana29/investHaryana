
CREATE TABLE IF NOT EXISTS environment_impactdetail (
    id uuid,
    projectid uuid,
    source_of_water_supply uuid,
    water_process int,
    water_cooling int,
    water_domestic int,
    water_other int,
    waste_water_process int,
    waste_water_cooling int,
    waste_water_domesting int,
    waste_water_other int,
    waste_water_treatment text,
    mode_of_disposal_for_discharge uuid,
    emissionid uuid,
    wastewaterdetailid uuid,
    document_attached text,
    PRIMARY KEY(id)
);
