
CREATE TABLE IF NOT EXISTS projectdetailcombinecodes (
    id uuid,
    investorid uuid,
    companydetailid uuid,
    projectsitedetailid uuid,
    projectfinanceid uuid,
    manufacturingid uuid,
    electricityrequirementid uuid,
    environmentimpactdetailid uuid,
    termdeclarationacceptid uuid,
    PRIMARY KEY(id)
);
