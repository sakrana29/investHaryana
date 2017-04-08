
CREATE TABLE IF NOT EXISTS projectServicePaymentDetails (
    id uuid,
    projectid uuid,
    serviceid uuid,
    paymentMade decimal,
    paymentMadeBy text,
    paymentDate timestamp,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS projectServicePaymentDetailsByProjectAndService (
projectid uuid,
serviceid uuid,
id uuid,
PRIMARY KEY(projectid,serviceid,id)
);
