
CREATE TABLE IF NOT EXISTS projectServiceReportInfo (
    id uuid,
    projectid uuid,
    projecttype text,
    departmentname text,
    servicename text,
    assignDate timestamp,
    requireDate timestamp,
    status text,
    stage text,
    investorName text,
    cafPin text,
    finalAction text,
    finalActionDate timestamp,
    projectInvestment double,
    projectEmployment text,
    PRIMARY KEY(id)
);
