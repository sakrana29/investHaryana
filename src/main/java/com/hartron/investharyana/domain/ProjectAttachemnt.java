package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A ProjectAttachemnt.
 */

@Table(name = "projectAttachemnt")
public class ProjectAttachemnt implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String fileName;

    private String description;

    private String fileExtension;

    private String serverFileName;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public ProjectAttachemnt fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public ProjectAttachemnt description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public ProjectAttachemnt fileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
        return this;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getServerFileName() {
        return serverFileName;
    }

    public ProjectAttachemnt serverFileName(String serverFileName) {
        this.serverFileName = serverFileName;
        return this;
    }

    public void setServerFileName(String serverFileName) {
        this.serverFileName = serverFileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProjectAttachemnt projectAttachemnt = (ProjectAttachemnt) o;
        if (projectAttachemnt.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectAttachemnt.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectAttachemnt{" +
            "id=" + id +
            ", fileName='" + fileName + "'" +
            ", description='" + description + "'" +
            ", fileExtension='" + fileExtension + "'" +
            ", serverFileName='" + serverFileName + "'" +
            '}';
    }
}
