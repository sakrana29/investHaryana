package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A Term_declaration_accept.
 */

@Table(name = "term_declaration_accept")
public class Term_declaration_accept implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID projectid;

    private Boolean acceptance;

    private ZonedDateTime applicationdate;

    private String place;

    private ByteBuffer signature;

    @Column(name = "signature_content_type")
    private String signatureContentType;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProjectid() {
        return projectid;
    }

    public Term_declaration_accept projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    public Boolean isAcceptance() {
        return acceptance;
    }

    public Term_declaration_accept acceptance(Boolean acceptance) {
        this.acceptance = acceptance;
        return this;
    }

    public void setAcceptance(Boolean acceptance) {
        this.acceptance = acceptance;
    }

    public ZonedDateTime getApplicationdate() {
        return applicationdate;
    }

    public Term_declaration_accept applicationdate(ZonedDateTime applicationdate) {
        this.applicationdate = applicationdate;
        return this;
    }

    public void setApplicationdate(ZonedDateTime applicationdate) {
        this.applicationdate = applicationdate;
    }

    public String getPlace() {
        return place;
    }

    public Term_declaration_accept place(String place) {
        this.place = place;
        return this;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public ByteBuffer getSignature() {
        return signature;
    }

    public Term_declaration_accept signature(ByteBuffer signature) {
        this.signature = signature;
        return this;
    }

    public void setSignature(ByteBuffer signature) {
        this.signature = signature;
    }

    public String getSignatureContentType() {
        return signatureContentType;
    }

    public Term_declaration_accept signatureContentType(String signatureContentType) {
        this.signatureContentType = signatureContentType;
        return this;
    }

    public void setSignatureContentType(String signatureContentType) {
        this.signatureContentType = signatureContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Term_declaration_accept term_declaration_accept = (Term_declaration_accept) o;
        if (term_declaration_accept.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, term_declaration_accept.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Term_declaration_accept{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", acceptance='" + acceptance + "'" +
            ", applicationdate='" + applicationdate + "'" +
            ", place='" + place + "'" +
            ", signature='" + signature + "'" +
            ", signatureContentType='" + signatureContentType + "'" +
            '}';
    }
}
