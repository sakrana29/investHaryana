package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Landusezoneclassification entity.
 */
public class LandusezoneclassificationDTO implements Serializable {

    private UUID id;

    private String landzoneclassificationtype;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getLandzoneclassificationtype() {
        return landzoneclassificationtype;
    }

    public void setLandzoneclassificationtype(String landzoneclassificationtype) {
        this.landzoneclassificationtype = landzoneclassificationtype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LandusezoneclassificationDTO landusezoneclassificationDTO = (LandusezoneclassificationDTO) o;

        if ( ! Objects.equals(id, landusezoneclassificationDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LandusezoneclassificationDTO{" +
            "id=" + id +
            ", landzoneclassificationtype='" + landzoneclassificationtype + "'" +
            '}';
    }
}
