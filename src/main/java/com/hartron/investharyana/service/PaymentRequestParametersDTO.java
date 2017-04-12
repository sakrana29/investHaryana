package com.hartron.investharyana.service;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Payment Request Parameters.
 */
public class PaymentRequestParametersDTO implements Serializable {

    private String firstname;

    private String key;

}
