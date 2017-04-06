package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the ProjectAttachemnt entity.
 */
public class ProjectAttachemntDTO implements Serializable {

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

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
    public String getServerFileName() {
        return serverFileName;
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

        ProjectAttachemntDTO projectAttachemntDTO = (ProjectAttachemntDTO) o;

        if ( ! Objects.equals(id, projectAttachemntDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectAttachemntDTO{" +
            "id=" + id +
            ", fileName='" + fileName + "'" +
            ", description='" + description + "'" +
            ", fileExtension='" + fileExtension + "'" +
            ", serverFileName='" + serverFileName + "'" +
            '}';
    }
}
