
CREATE TABLE IF NOT EXISTS projectdetail (
    id uuid,
    projectpurpose text,
    niccode text,
    existing_regulatory_approval boolean,
    edc_sif_clu_fee_paid_applicable boolean,
    approval_application_form text,
    category_of_project text,
    collaboration_with_foreign_country text,
    projectype text,
    sectorname text,
    size_of_industry text,
    createdate timestamp,
    updatedate timestamp,
    sectorother text,
    PRIMARY KEY(id)
);
