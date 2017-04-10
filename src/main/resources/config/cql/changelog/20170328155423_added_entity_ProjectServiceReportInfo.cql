
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

CREATE TABLE IF NOT EXISTS projectServiceReportInfo_By_DepartmentName (
id uuid,
departmentname text,
PRIMARY KEY(departmentname,id)
);

CREATE TABLE IF NOT EXISTS projectServiceReportByProjectDeptService (
projectid uuid,
departmentname text,
servicename text,
id uuid,
PRIMARY KEY(projectid, departmentname, servicename, id)
);

