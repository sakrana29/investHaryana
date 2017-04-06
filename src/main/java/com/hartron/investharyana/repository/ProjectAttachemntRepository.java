package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.ProjectAttachemnt;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the ProjectAttachemnt entity.
 */
@Repository
public class ProjectAttachemntRepository {

    private final Session session;

    private Mapper<ProjectAttachemnt> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ProjectAttachemntRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(ProjectAttachemnt.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectAttachemnt");
        this.truncateStmt = session.prepare("TRUNCATE projectAttachemnt");
    }

    public List<ProjectAttachemnt> findAll() {
        List<ProjectAttachemnt> projectAttachemntsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                ProjectAttachemnt projectAttachemnt = new ProjectAttachemnt();
                projectAttachemnt.setId(row.getUUID("id"));
                projectAttachemnt.setFileName(row.getString("fileName"));
                projectAttachemnt.setDescription(row.getString("description"));
                projectAttachemnt.setFileExtension(row.getString("fileExtension"));
                projectAttachemnt.setServerFileName(row.getString("serverFileName"));
                return projectAttachemnt;
            }
        ).forEach(projectAttachemntsList::add);
        return projectAttachemntsList;
    }

    public ProjectAttachemnt findOne(UUID id) {
        return mapper.get(id);
    }

    public ProjectAttachemnt save(ProjectAttachemnt projectAttachemnt) {
        if (projectAttachemnt.getId() == null) {
            projectAttachemnt.setId(UUID.randomUUID());
        }
        mapper.save(projectAttachemnt);
        return projectAttachemnt;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
