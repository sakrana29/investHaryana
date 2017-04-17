
CREATE TABLE IF NOT EXISTS projectServicePaymentDetails (
    id uuid,
    projectid uuid,
    serviceid uuid,
    paymentMade decimal,
    paymentMadeBy text,
    paymentDate timestamp,
    transactionId text,
    paymentResponse text,
    PRIMARY KEY(id)
);
