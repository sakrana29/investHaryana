package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Manufacturingdetail entity.
 */
public class ManufacturingdetailDTO implements Serializable {

    private UUID id;

    private UUID projectid;

    private UUID projectrawmaterialid;

    private UUID productid;

    private UUID processid;

    private ByteBuffer manufacturing_flow_document;
    private String manufacturing_flow_documentContentType;

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
    public UUID getProjectrawmaterialid() {
        return projectrawmaterialid;
    }

    public void setProjectrawmaterialid(UUID projectrawmaterialid) {
        this.projectrawmaterialid = projectrawmaterialid;
    }
    public UUID getProductid() {
        return productid;
    }

    public void setProductid(UUID productid) {
        this.productid = productid;
    }
    public UUID getProcessid() {
        return processid;
    }

    public void setProcessid(UUID processid) {
        this.processid = processid;
    }
    public ByteBuffer getManufacturing_flow_document() {
        return manufacturing_flow_document;
    }

    public void setManufacturing_flow_document(ByteBuffer manufacturing_flow_document) {
        this.manufacturing_flow_document = manufacturing_flow_document;
    }

    public String getManufacturing_flow_documentContentType() {
        return manufacturing_flow_documentContentType;
    }

    public void setManufacturing_flow_documentContentType(String manufacturing_flow_documentContentType) {
        this.manufacturing_flow_documentContentType = manufacturing_flow_documentContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ManufacturingdetailDTO manufacturingdetailDTO = (ManufacturingdetailDTO) o;

        if ( ! Objects.equals(id, manufacturingdetailDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ManufacturingdetailDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", projectrawmaterialid='" + projectrawmaterialid + "'" +
            ", productid='" + productid + "'" +
            ", processid='" + processid + "'" +
            ", manufacturing_flow_document='" + manufacturing_flow_document + "'" +
            '}';
    }
}
