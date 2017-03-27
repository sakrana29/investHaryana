
CREATE TABLE IF NOT EXISTS investor (
    id uuid,
    mouapplicable boolean,
    mousignyear int,
    mouidnumber text,
    firstname text,
    middlename text,
    lastname text,
    address1 text,
    address2 text,
    address3 text,
    emailprimary text,
    emailsecondary text,
    moudocument text,
    investorpicpath text,
    userlogin text,
    cityname text,
    countryname text,
    statename text,
    PRIMARY KEY(id)
);
