package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Wastewaterdetail;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Wastewaterdetail entity.
 */
@Repository
public class WastewaterdetailRepository {

    private final Session session;

    private Mapper<Wastewaterdetail> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public WastewaterdetailRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Wastewaterdetail.class);
        this.findAllStmt = session.prepare("SELECT * FROM wastewaterdetail");
        this.truncateStmt = session.prepare("TRUNCATE wastewaterdetail");
    }

    public List<Wastewaterdetail> findAll() {
        List<Wastewaterdetail> wastewaterdetailsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Wastewaterdetail wastewaterdetail = new Wastewaterdetail();
                wastewaterdetail.setId(row.getUUID("id"));
                wastewaterdetail.setProjectid(row.getUUID("projectid"));
                wastewaterdetail.setSource_of_generation(row.getString("source_of_generation"));
                wastewaterdetail.setNaturetype(row.getUUID("naturetype"));
                wastewaterdetail.setQuantity(row.getInt("quantity"));
                wastewaterdetail.setMode_of_disposal(row.getUUID("mode_of_disposal"));
                wastewaterdetail.setDescription(row.getString("description"));
                wastewaterdetail.setProjectname(row.getString("projectname"));
                wastewaterdetail.setNaturetypename(row.getString("naturetypename"));
                wastewaterdetail.setModeofdisposal(row.getString("modeofdisposal"));
                return wastewaterdetail;
            }
        ).forEach(wastewaterdetailsList::add);
        return wastewaterdetailsList;
    }

    public Wastewaterdetail findOne(UUID id) {
        return mapper.get(id);
    }

    public Wastewaterdetail save(Wastewaterdetail wastewaterdetail) {
        if (wastewaterdetail.getId() == null) {
            wastewaterdetail.setId(UUID.randomUUID());
        }
        mapper.save(wastewaterdetail);
        return wastewaterdetail;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
