package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Projectdetailcombinecodes;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Projectdetailcombinecodes entity.
 */
@Repository
public class ProjectdetailcombinecodesRepository {

    private final Session session;

    private Mapper<Projectdetailcombinecodes> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ProjectdetailcombinecodesRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Projectdetailcombinecodes.class);
        this.findAllStmt = session.prepare("SELECT * FROM projectdetailcombinecodes");
        this.truncateStmt = session.prepare("TRUNCATE projectdetailcombinecodes");
    }

    public List<Projectdetailcombinecodes> findAll() {
        List<Projectdetailcombinecodes> projectdetailcombinecodesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Projectdetailcombinecodes projectdetailcombinecodes = new Projectdetailcombinecodes();
                projectdetailcombinecodes.setId(row.getUUID("id"));
                projectdetailcombinecodes.setInvestorid(row.getUUID("investorid"));
                projectdetailcombinecodes.setCompanydetailid(row.getUUID("companydetailid"));
                projectdetailcombinecodes.setProjectsitedetailid(row.getUUID("projectsitedetailid"));
                projectdetailcombinecodes.setProjectfinanceid(row.getUUID("projectfinanceid"));
                projectdetailcombinecodes.setManufacturingid(row.getUUID("manufacturingid"));
                projectdetailcombinecodes.setElectricityrequirementid(row.getUUID("electricityrequirementid"));
                return projectdetailcombinecodes;
            }
        ).forEach(projectdetailcombinecodesList::add);
        return projectdetailcombinecodesList;
    }

    public Projectdetailcombinecodes findOne(UUID id) {
        return mapper.get(id);
    }

    public Projectdetailcombinecodes save(Projectdetailcombinecodes projectdetailcombinecodes) {
        if (projectdetailcombinecodes.getId() == null) {
            projectdetailcombinecodes.setId(UUID.randomUUID());
        }
        mapper.save(projectdetailcombinecodes);
        return projectdetailcombinecodes;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
