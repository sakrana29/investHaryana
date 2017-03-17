
CREATE TABLE IF NOT EXISTS city_town_village (
    id uuid,
    blockid uuid,
    city_town_village_name text,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS village_by_block (
blockid uuid,
id uuid,
PRIMARY KEY(blockid,id)
);
