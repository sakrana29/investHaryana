package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Block.
 */

@Table(name = "block")
public class Block implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    @NotNull
    private String blockname;

    private String districtname;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBlockname() {
        return blockname;
    }

    public Block blockname(String blockname) {
        this.blockname = blockname;
        return this;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }

    public String getDistrictname() {
        return districtname;
    }

    public Block districtname(String districtname) {
        this.districtname = districtname;
        return this;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Block block = (Block) o;
        if (block.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, block.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Block{" +
            "id=" + id +
            ", blockname='" + blockname + "'" +
            ", districtname='" + districtname + "'" +
            '}';
    }
}
