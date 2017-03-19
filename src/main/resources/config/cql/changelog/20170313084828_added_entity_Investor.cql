
CREATE TABLE IF NOT EXISTS investor (
    id uuid,
    mouapplicable boolean,
    mousignyear int,
    mouidnumber text,
    firstname text,
    middlename text,
    lastname text,
    countryid uuid,
    stateid uuid,
    cityid uuid,
    address1 text,
    address2 text,
    address3 text,
    emailprimary text,
    emailsecondary text,
    moudocument text,
    investorpicpath text,
    userlogin text,
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS investor_by_userlogin (
userlogin text,
id uuid,
PRIMARY KEY(userlogin,id)
);
