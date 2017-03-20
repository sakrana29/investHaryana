
CREATE TABLE IF NOT EXISTS projectdetail (
    id uuid,
    projectpurpose text,
    niccode text,
    existing_regulatory_approval boolean,
    edc_sif_clu_fee_paid_applicable boolean,
    detail_project_report text,
    approval_document text,
    edc_sif_clu_fee_paid_document text,
    approval_application_form uuid,
    category_of_project uuid,
    collaboration_with_foreign_country uuid,
    investorid uuid,
    projectype uuid,
    sectorid uuid,
    size_of_industry uuid,
    PRIMARY KEY(id)
);
