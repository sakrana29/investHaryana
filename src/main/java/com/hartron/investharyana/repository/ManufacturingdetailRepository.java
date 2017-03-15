package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Manufacturingdetail;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Manufacturingdetail entity.
 */
@Repository
public class ManufacturingdetailRepository {

    private final Session session;

    private Mapper<Manufacturingdetail> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ManufacturingdetailRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Manufacturingdetail.class);
        this.findAllStmt = session.prepare("SELECT * FROM manufacturingdetail");
        this.truncateStmt = session.prepare("TRUNCATE manufacturingdetail");
    }

    public List<Manufacturingdetail> findAll() {
        List<Manufacturingdetail> manufacturingdetailsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Manufacturingdetail manufacturingdetail = new Manufacturingdetail();
                manufacturingdetail.setId(row.getUUID("id"));
                manufacturingdetail.setProjectid(row.getUUID("projectid"));
                manufacturingdetail.setProjectrawmaterialid(row.getUUID("projectrawmaterialid"));
                manufacturingdetail.setProductid(row.getUUID("productid"));
                manufacturingdetail.setProcessid(row.getUUID("processid"));
                manufacturingdetail.setManufacturing_flow_document(row.getBytes("manufacturing_flow_document"));
                manufacturingdetail.setManufacturing_flow_documentContentType(row.getString("manufacturing_flow_document_content_type"));

                return manufacturingdetail;
            }
        ).forEach(manufacturingdetailsList::add);
        return manufacturingdetailsList;
    }

    public Manufacturingdetail findOne(UUID id) {
        return mapper.get(id);
    }

    public Manufacturingdetail save(Manufacturingdetail manufacturingdetail) {
        if (manufacturingdetail.getId() == null) {
            manufacturingdetail.setId(UUID.randomUUID());
        }
        mapper.save(manufacturingdetail);
        return manufacturingdetail;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
