
CREATE TABLE IF NOT EXISTS manufacturingdetail (
    id uuid,
    projectid uuid,
    projectrawmaterialid uuid,
    productid uuid,
    processid uuid,
    manufacturing_flow_document text,
    PRIMARY KEY(id)
);
