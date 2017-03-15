
CREATE TABLE IF NOT EXISTS manufacturingdetail (
    id uuid,
    projectid uuid,
    projectrawmaterialid uuid,
    productid uuid,
    processid uuid,
    manufacturing_flow_document blob,
    manufacturing_flow_document_content_type text,
    PRIMARY KEY(id)
);
