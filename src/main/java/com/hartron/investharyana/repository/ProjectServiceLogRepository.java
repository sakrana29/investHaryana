package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.ProjectServiceLog;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the ProjectServiceLog entity.
 */
@Repository
public class ProjectServiceLogRepository {

    private final Session session;

    private Mapper<ProjectServiceLog> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ProjectServiceLogRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(ProjectServiceLog.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectServiceLog");
        this.truncateStmt = session.prepare("TRUNCATE projectServiceLog");
    }

    public List<ProjectServiceLog> findAll() {
        List<ProjectServiceLog> projectServiceLogsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                ProjectServiceLog projectServiceLog = new ProjectServiceLog();
                projectServiceLog.setId(row.getUUID("id"));
                projectServiceLog.setProjectid(row.getUUID("projectid"));
                projectServiceLog.setServiceid(row.getUUID("serviceid"));
                projectServiceLog.setComments(row.getString("comments"));
                projectServiceLog.setCommentDate(row.get("commentDate", ZonedDateTime.class));
                projectServiceLog.setCommentByUserLogin(row.getString("commentByUserLogin"));
                return projectServiceLog;
            }
        ).forEach(projectServiceLogsList::add);
        return projectServiceLogsList;
    }

    public ProjectServiceLog findOne(UUID id) {
        return mapper.get(id);
    }

    public ProjectServiceLog save(ProjectServiceLog projectServiceLog) {
        if (projectServiceLog.getId() == null) {
            projectServiceLog.setId(UUID.randomUUID());
        }
        mapper.save(projectServiceLog);
        return projectServiceLog;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
