
CREATE TABLE IF NOT EXISTS wastewaterdetail (
    id uuid,
    projectid uuid,
    source_of_generation text,
    naturetype uuid,
    quantity int,
    mode_of_disposal uuid,
    description text,
    PRIMARY KEY(id)
);
