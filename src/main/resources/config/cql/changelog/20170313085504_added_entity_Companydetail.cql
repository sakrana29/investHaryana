
CREATE TABLE IF NOT EXISTS companydetail (
    id uuid,
    promoter_md_director text,
    designation text,
    businessentity text,
    director_promoter_md_ceo_number int,
    pan_number text,
    aadhar_number text,
    nri boolean,
    tin_vat_number text,
    cst_number text,
    businessentitytype text,
    createdate timestamp,
    updatedate timestamp,
    PRIMARY KEY(id)
);
