package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A ProjectServiceLog.
 */

@Table(name = "projectServiceLog")
public class ProjectServiceLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
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

    public ProjectServiceLog projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    public UUID getServiceid() {
        return serviceid;
    }

    public ProjectServiceLog serviceid(UUID serviceid) {
        this.serviceid = serviceid;
        return this;
    }

    public void setServiceid(UUID serviceid) {
        this.serviceid = serviceid;
    }

    public String getComments() {
        return comments;
    }

    public ProjectServiceLog comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ZonedDateTime getCommentDate() {
        return commentDate;
    }

    public ProjectServiceLog commentDate(ZonedDateTime commentDate) {
        this.commentDate = commentDate;
        return this;
    }

    public void setCommentDate(ZonedDateTime commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentByUserLogin() {
        return commentByUserLogin;
    }

    public ProjectServiceLog commentByUserLogin(String commentByUserLogin) {
        this.commentByUserLogin = commentByUserLogin;
        return this;
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
        ProjectServiceLog projectServiceLog = (ProjectServiceLog) o;
        if (projectServiceLog.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectServiceLog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectServiceLog{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", serviceid='" + serviceid + "'" +
            ", comments='" + comments + "'" +
            ", commentDate='" + commentDate + "'" +
            ", commentByUserLogin='" + commentByUserLogin + "'" +
            '}';
    }
}
