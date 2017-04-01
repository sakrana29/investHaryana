package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
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

    public ZonedDateTime getCreatedate() {
        return createdate;
    }

    public Term_declaration_accept createdate(ZonedDateTime createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(ZonedDateTime createdate) {
        this.createdate = createdate;
    }

    public ZonedDateTime getUpdatedate() {
        return updatedate;
    }

    public Term_declaration_accept updatedate(ZonedDateTime updatedate) {
        this.updatedate = updatedate;
        return this;
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
            ", acceptance='" + acceptance + "'" +
            ", applicationdate='" + applicationdate + "'" +
            ", place='" + place + "'" +
            ", createdate='" + createdate + "'" +
            ", updatedate='" + updatedate + "'" +
            '}';
    }
}
