
CREATE TABLE IF NOT EXISTS projectServiceLog (
    id uuid,
    projectid uuid,
    serviceid uuid,
    comments text,
    commentDate timestamp,
    commentByUserLogin text,
    PRIMARY KEY(id)
);
