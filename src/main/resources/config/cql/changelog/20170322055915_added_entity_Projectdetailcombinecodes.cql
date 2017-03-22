
CREATE TABLE IF NOT EXISTS projectdetailcombinecodes (
    id uuid,
    investorid uuid,
    companydetailid uuid,
    projectsitedetailid uuid,
    projectfinanceid uuid,
    manufacturingid uuid,
    electricityrequirementid uuid,
    PRIMARY KEY(id)
);
