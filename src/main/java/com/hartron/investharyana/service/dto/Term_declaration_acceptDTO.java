package com.hartron.investharyana.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Term_declaration_accept entity.
 */
public class Term_declaration_acceptDTO implements Serializable {

    private UUID id;

    private UUID projectid;

    private Boolean acceptance;

    private ZonedDateTime applicationdate;

    private String place;

    private ByteBuffer signature;
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

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }
    public Boolean getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(Boolean acceptance) {
        this.acceptance = acceptance;
    }
    public ZonedDateTime getApplicationdate() {
        return applicationdate;
    }

    public void setApplicationdate(ZonedDateTime applicationdate) {
        this.applicationdate = applicationdate;
    }
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    public ByteBuffer getSignature() {
        return signature;
    }

    public void setSignature(ByteBuffer signature) {
        this.signature = signature;
    }

    public String getSignatureContentType() {
        return signatureContentType;
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

        Term_declaration_acceptDTO term_declaration_acceptDTO = (Term_declaration_acceptDTO) o;

        if ( ! Objects.equals(id, term_declaration_acceptDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Term_declaration_acceptDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", acceptance='" + acceptance + "'" +
            ", applicationdate='" + applicationdate + "'" +
            ", place='" + place + "'" +
            ", signature='" + signature + "'" +
            '}';
    }
}
