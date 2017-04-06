
CREATE TABLE IF NOT EXISTS projectservicedetail (
    id uuid,
    projectid uuid,
    serviceid uuid,
    isRequired boolean,
    requireMarkedOnDate timestamp,
    requireMarkedBy text,
    isAssigned boolean,
    assigOnDate timestamp,
    assignBy text,
    formFilledStatus boolean,
    isPaymentMade boolean,
    isPaymentVerified boolean,
    formFilledOnDate timestamp,
    formFilledBy text,
    paymentMadeOnDate timestamp,
    status text,
    latestComments text,
    serviceFee decimal,
    PRIMARY KEY(id)
);
