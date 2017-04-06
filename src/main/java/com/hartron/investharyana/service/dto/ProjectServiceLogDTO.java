package com.hartron.investharyana.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the ProjectServiceLog entity.
 */
public class ProjectServiceLogDTO implements Serializable {

    private UUID id;

    private UUID projectid;

    private UUID serviceid;

    private String comments;

    private ZonedDateTime commentDate;

    private String commentByUserLogin;

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
    public UUID getServiceid() {
        return serviceid;
    }

    public void setServiceid(UUID serviceid) {
        this.serviceid = serviceid;
    }
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    public ZonedDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(ZonedDateTime commentDate) {
        this.commentDate = commentDate;
    }
    public String getCommentByUserLogin() {
        return commentByUserLogin;
    }

    public void setCommentByUserLogin(String commentByUserLogin) {
        this.commentByUserLogin = commentByUserLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectServiceLogDTO projectServiceLogDTO = (ProjectServiceLogDTO) o;

        if ( ! Objects.equals(id, projectServiceLogDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectServiceLogDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", serviceid='" + serviceid + "'" +
            ", comments='" + comments + "'" +
            ", commentDate='" + commentDate + "'" +
            ", commentByUserLogin='" + commentByUserLogin + "'" +
            '}';
    }
}
