
CREATE TABLE IF NOT EXISTS environment_impactdetail (
    id uuid,
    water_process int,
    water_cooling int,
    water_domestic int,
    water_other int,
    waste_water_process int,
    waste_water_cooling int,
    waste_water_domesting int,
    waste_water_other int,
    source_of_water_supply text,
    mode_of_disposal_for_discharge text,
    recycling_process text,
    recycling_cooling text,
    recycling_domestic text,
    recycling_other text,
    createdate timestamp,
    updatedate timestamp,
    sourcewatersupplyother text,
    modedisposalother text,
    PRIMARY KEY(id)
);
