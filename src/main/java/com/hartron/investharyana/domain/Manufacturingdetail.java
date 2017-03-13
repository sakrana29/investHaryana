package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * A Manufacturingdetail.
 */

@Table(name = "manufacturingdetail")
public class Manufacturingdetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID projectid;

    private UUID projectrawmaterialid;

    private UUID productid;

    private UUID processid;

    private ByteBuffer manufacturing_flow_document;

    @Column(name = "manufacturing_flow_document_content_type")
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

    public Manufacturingdetail projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    public UUID getProjectrawmaterialid() {
        return projectrawmaterialid;
    }

    public Manufacturingdetail projectrawmaterialid(UUID projectrawmaterialid) {
        this.projectrawmaterialid = projectrawmaterialid;
        return this;
    }

    public void setProjectrawmaterialid(UUID projectrawmaterialid) {
        this.projectrawmaterialid = projectrawmaterialid;
    }

    public UUID getProductid() {
        return productid;
    }

    public Manufacturingdetail productid(UUID productid) {
        this.productid = productid;
        return this;
    }

    public void setProductid(UUID productid) {
        this.productid = productid;
    }

    public UUID getProcessid() {
        return processid;
    }

    public Manufacturingdetail processid(UUID processid) {
        this.processid = processid;
        return this;
    }

    public void setProcessid(UUID processid) {
        this.processid = processid;
    }

    public ByteBuffer getManufacturing_flow_document() {
        return manufacturing_flow_document;
    }

    public Manufacturingdetail manufacturing_flow_document(ByteBuffer manufacturing_flow_document) {
        this.manufacturing_flow_document = manufacturing_flow_document;
        return this;
    }

    public void setManufacturing_flow_document(ByteBuffer manufacturing_flow_document) {
        this.manufacturing_flow_document = manufacturing_flow_document;
    }

    public String getManufacturing_flow_documentContentType() {
        return manufacturing_flow_documentContentType;
    }

    public Manufacturingdetail manufacturing_flow_documentContentType(String manufacturing_flow_documentContentType) {
        this.manufacturing_flow_documentContentType = manufacturing_flow_documentContentType;
        return this;
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
        Manufacturingdetail manufacturingdetail = (Manufacturingdetail) o;
        if (manufacturingdetail.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, manufacturingdetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Manufacturingdetail{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", projectrawmaterialid='" + projectrawmaterialid + "'" +
            ", productid='" + productid + "'" +
            ", processid='" + processid + "'" +
            ", manufacturing_flow_document='" + manufacturing_flow_document + "'" +
            ", manufacturing_flow_documentContentType='" + manufacturing_flow_documentContentType + "'" +
            '}';
    }
}
