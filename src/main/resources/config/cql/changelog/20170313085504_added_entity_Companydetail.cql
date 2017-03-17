
CREATE TABLE IF NOT EXISTS companydetail (
    id uuid,
    investorid uuid,
    promoter_md_director text,
    designation text,
    businessentity text,
    businessentitytype uuid,
    director_promoter_md_ceo_number int,
    pan_number text,
    aadhar_number text,
    nri boolean,
    tin_vat_number text,
    cst_number text,
    director_md_ceo_list text,
    pancard text,
    aadharcard text,
    tin_vat_document text,
    cst_document text,
    moa_partnershipdeed text,
    registration_document text,
    PRIMARY KEY(id)
);
