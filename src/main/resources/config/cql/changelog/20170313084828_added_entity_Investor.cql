
CREATE TABLE IF NOT exists investor (
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
    userlogin text,
    cityname text,
    countryname text,
    statename text,
    pincode int,
    phonenumber double,
    mobilenumber double,
    cafpin double,
    createdate timestamp,
    updatedate timestamp,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT exists investor_by_cafpin (
cafpin double,
id uuid,
PRIMARY KEY(cafpin, id)

);
