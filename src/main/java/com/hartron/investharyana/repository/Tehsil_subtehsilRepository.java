package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Tehsil_subtehsil;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Cassandra repository for the Tehsil_subtehsil entity.
 */
@Repository
public class Tehsil_subtehsilRepository {

    private final Session session;

    private Mapper<Tehsil_subtehsil> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    private PreparedStatement insertByDistrictStmt;

    private PreparedStatement findByDistrictStmt;

    public Tehsil_subtehsilRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Tehsil_subtehsil.class);
        this.findAllStmt = session.prepare("SELECT * FROM tehsil_subtehsil");
        this.truncateStmt = session.prepare("TRUNCATE tehsil_subtehsil");

        this.insertByDistrictStmt = session.prepare(
            "INSERT INTO tehsil_by_district (districtid, id) " +
                "VALUES (:districtid, :id)");

        this.findByDistrictStmt = session.prepare(
            "SELECT id " +
                "FROM tehsil_by_district " +
                "WHERE districtid = :districtid");
    }

    public List<Tehsil_subtehsil> findAll() {
        List<Tehsil_subtehsil> tehsil_subtehsilsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Tehsil_subtehsil tehsil_subtehsil = new Tehsil_subtehsil();
                tehsil_subtehsil.setId(row.getUUID("id"));
                tehsil_subtehsil.setDistrictid(row.getUUID("districtid"));
                tehsil_subtehsil.setTehsil_subtehsilname(row.getString("tehsil_subtehsilname"));
                return tehsil_subtehsil;
            }
        ).forEach(tehsil_subtehsilsList::add);
        return tehsil_subtehsilsList;
    }

    public Tehsil_subtehsil findOne(UUID id) {
        return mapper.get(id);
    }

    public List<Tehsil_subtehsil> findTehsilByDistrictId(UUID districtid) {
        BoundStatement stmt = findByDistrictStmt.bind();
        stmt.setUUID("districtid", districtid);
        return findTehsilFromIndex(stmt);
    }

    private List<Tehsil_subtehsil> findTehsilFromIndex(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        List<Tehsil_subtehsil> tehsilList=new ArrayList<>();

        while(!(rs.isExhausted())){
            Tehsil_subtehsil tehsil=new Tehsil_subtehsil();
            tehsil=(Optional.ofNullable(rs.one().getUUID("id"))
                .map(id -> Optional.ofNullable(mapper.get(id)))
                .get()).get();
            tehsilList.add(tehsil);
        }
        return tehsilList;

    }

    public Tehsil_subtehsil save(Tehsil_subtehsil tehsil_subtehsil) {
        if (tehsil_subtehsil.getId() == null) {
            tehsil_subtehsil.setId(UUID.randomUUID());
        }
        mapper.save(tehsil_subtehsil);

        BatchStatement batch = new BatchStatement();
        batch.add(insertByDistrictStmt.bind()
            .setUUID("districtid", tehsil_subtehsil.getDistrictid())
            .setUUID("id", tehsil_subtehsil.getId()));
        session.execute(batch);

        return tehsil_subtehsil;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
