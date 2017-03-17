package com.hartron.investharyana.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Connectingroad entity.
 */
public class ConnectingroadDTO implements Serializable {

    private UUID id;

    @NotNull
    private String connectingraodtype;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getConnectingraodtype() {
        return connectingraodtype;
    }

    public void setConnectingraodtype(String connectingraodtype) {
        this.connectingraodtype = connectingraodtype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConnectingroadDTO connectingroadDTO = (ConnectingroadDTO) o;

        if ( ! Objects.equals(id, connectingroadDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ConnectingroadDTO{" +
            "id=" + id +
            ", connectingraodtype='" + connectingraodtype + "'" +
            '}';
    }
}
