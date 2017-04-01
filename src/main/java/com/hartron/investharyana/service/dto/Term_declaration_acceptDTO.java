package com.hartron.investharyana.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Term_declaration_accept entity.
 */
public class Term_declaration_acceptDTO implements Serializable {

    private UUID id;

    private Boolean acceptance;

    private ZonedDateTime applicationdate;

    private String place;

    private ZonedDateTime createdate;

    private ZonedDateTime updatedate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
    public ZonedDateTime getCreatedate() {
        return createdate;
    }

    public void setCreatedate(ZonedDateTime createdate) {
        this.createdate = createdate;
    }
    public ZonedDateTime getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(ZonedDateTime updatedate) {
        this.updatedate = updatedate;
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
            ", acceptance='" + acceptance + "'" +
            ", applicationdate='" + applicationdate + "'" +
            ", place='" + place + "'" +
            ", createdate='" + createdate + "'" +
            ", updatedate='" + updatedate + "'" +
            '}';
    }
}
