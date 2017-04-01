package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Environment_impactdetail;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Environment_impactdetail entity.
 */
@Repository
public class Environment_impactdetailRepository {

    private final Session session;

    private Mapper<Environment_impactdetail> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Environment_impactdetailRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Environment_impactdetail.class);
        this.findAllStmt = session.prepare("SELECT * FROM environment_impactdetail");
        this.truncateStmt = session.prepare("TRUNCATE environment_impactdetail");
    }

    public List<Environment_impactdetail> findAll() {
        List<Environment_impactdetail> environment_impactdetailsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Environment_impactdetail environment_impactdetail = new Environment_impactdetail();
                environment_impactdetail.setId(row.getUUID("id"));
                environment_impactdetail.setWater_process(row.getInt("water_process"));
                environment_impactdetail.setWater_cooling(row.getInt("water_cooling"));
                environment_impactdetail.setWater_domestic(row.getInt("water_domestic"));
                environment_impactdetail.setWater_other(row.getInt("water_other"));
                environment_impactdetail.setWaste_water_process(row.getInt("waste_water_process"));
                environment_impactdetail.setWaste_water_cooling(row.getInt("waste_water_cooling"));
                environment_impactdetail.setWaste_water_domesting(row.getInt("waste_water_domesting"));
                environment_impactdetail.setWaste_water_other(row.getInt("waste_water_other"));
                environment_impactdetail.setSource_of_water_supply(row.getString("source_of_water_supply"));
                environment_impactdetail.setMode_of_disposal_for_discharge(row.getString("mode_of_disposal_for_discharge"));
                environment_impactdetail.setRecycling_process(row.getString("recycling_process"));
                environment_impactdetail.setRecycling_cooling(row.getString("recycling_cooling"));
                environment_impactdetail.setRecycling_domestic(row.getString("recycling_domestic"));
                environment_impactdetail.setRecycling_other(row.getString("recycling_other"));
                environment_impactdetail.setCreatedate(row.get("createdate", ZonedDateTime.class));
                environment_impactdetail.setUpdatedate(row.get("updatedate", ZonedDateTime.class));
                environment_impactdetail.setSourcewatersupplyother(row.getString("sourcewatersupplyother"));
                environment_impactdetail.setModedisposalother(row.getString("modedisposalother"));
                return environment_impactdetail;
            }
        ).forEach(environment_impactdetailsList::add);
        return environment_impactdetailsList;
    }

    public Environment_impactdetail findOne(UUID id) {
        return mapper.get(id);
    }

    public Environment_impactdetail save(Environment_impactdetail environment_impactdetail) {
        if (environment_impactdetail.getId() == null) {
            environment_impactdetail.setId(UUID.randomUUID());
        }
        mapper.save(environment_impactdetail);
        return environment_impactdetail;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
