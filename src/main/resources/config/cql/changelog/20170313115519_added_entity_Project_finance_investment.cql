
CREATE TABLE IF NOT EXISTS project_finance_investment (
    id uuid,
    projectid uuid,
    land_cost decimal,
    building_cost decimal,
    machinery_cost decimal,
    misc_assests decimal,
    total_project_cost decimal,
    isfdi boolean,
    fdivalue decimal,
    fdi_country uuid,
    foreign_funding_source uuid,
    project_construction_start_date timestamp,
    commercial_activity_start_date timestamp,
    proposedproject_scheduleid uuid,
    PRIMARY KEY(id)
);
