
CREATE TABLE IF NOT EXISTS investor (
    id uuid,
    mouapplicable boolean,
    mousignyear int,
    moudocument blob,
    moudocument_content_type text,
    mouidnumber text,
    photo blob,
    photo_content_type text,
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
    PRIMARY KEY(id)
);
