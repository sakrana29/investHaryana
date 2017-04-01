
CREATE TABLE IF NOT EXISTS project_finance_investment (
    id uuid,
    land_cost decimal,
    building_cost decimal,
    machinery_cost decimal,
    misc_assests decimal,
    total_project_cost decimal,
    isfdi boolean,
    fdivalue decimal,
    project_construction_start_date timestamp,
    commercial_activity_start_date timestamp,
    fdi_country text,
    foreign_funding_source text,
    totalpurposedemployment int,
    createdate timestamp,
    updatedate timestamp,
    PRIMARY KEY(id)
);
