package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Watersupplysource entity.
 */
public class WatersupplysourceDTO implements Serializable {

    private UUID id;

    private String watersupplysourcetype;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getWatersupplysourcetype() {
        return watersupplysourcetype;
    }

    public void setWatersupplysourcetype(String watersupplysourcetype) {
        this.watersupplysourcetype = watersupplysourcetype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WatersupplysourceDTO watersupplysourceDTO = (WatersupplysourceDTO) o;

        if ( ! Objects.equals(id, watersupplysourceDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "WatersupplysourceDTO{" +
            "id=" + id +
            ", watersupplysourcetype='" + watersupplysourcetype + "'" +
            '}';
    }
}
