
CREATE TABLE IF NOT EXISTS projectdetail (
    id uuid,
    investorid uuid,
    sector uuid,
    projectpurpose text,
    size_of_industry uuid,
    projectype uuid,
    niccode text,
    category_of_project uuid,
    collaboration_with_foreign_country uuid,
    existing_regulatory_approval boolean,
    approval_application_form uuid,
    edc_sif_clu_fee_paid_applicable boolean,
    detail_project_report text,
    approval_document text,
    edc_sif_clu_fee_paid_document text,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS projectdetail_by_investor (
investorid uuid,
id uuid,
PRIMARY KEY(investorid,id)
);
