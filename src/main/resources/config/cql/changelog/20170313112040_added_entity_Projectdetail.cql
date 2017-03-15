
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
    detail_project_report blob,
    detail_project_report_content_type text,
    existing_regulatory_approval boolean,
    approval_application_form uuid,
    approval_document blob,
    approval_document_content_type text,
    edc_sif_clu_fee_paid_document blob,
    edc_sif_clu_fee_paid_document_content_type text,
    edc_sif_clu_fee_paid_applicable boolean,
    PRIMARY KEY(id)
);
