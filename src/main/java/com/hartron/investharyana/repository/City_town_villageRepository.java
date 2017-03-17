package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.City_town_village;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Cassandra repository for the City_town_village entity.
 */
@Repository
public class City_town_villageRepository {

    private final Session session;

    private Mapper<City_town_village> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    private PreparedStatement insertByBlockStmt;

    private PreparedStatement findByBlockStmt;

    public City_town_villageRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(City_town_village.class);
        this.findAllStmt = session.prepare("SELECT * FROM city_town_village");
        this.truncateStmt = session.prepare("TRUNCATE city_town_village");

        this.insertByBlockStmt = session.prepare(
            "INSERT INTO village_by_block (blockid, id) " +
                "VALUES (:blockid, :id)");

        this.findByBlockStmt = session.prepare(
            "SELECT id " +
                "FROM village_by_block " +
                "WHERE blockid = :blockid");
    }

    public List<City_town_village> findAll() {
        List<City_town_village> city_town_villagesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                City_town_village city_town_village = new City_town_village();
                city_town_village.setId(row.getUUID("id"));
                city_town_village.setBlockid(row.getUUID("blockid"));
                city_town_village.setCity_town_village_name(row.getString("city_town_village_name"));
                return city_town_village;
            }
        ).forEach(city_town_villagesList::add);
        return city_town_villagesList;
    }

    public City_town_village findOne(UUID id) {
        return mapper.get(id);
    }

    public List<City_town_village> findVillageByBlockId(UUID blockid) {
        BoundStatement stmt = findByBlockStmt.bind();
        stmt.setUUID("blockid", blockid);
        return findVillageFromIndex(stmt);
    }

    private List<City_town_village> findVillageFromIndex(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        List<City_town_village> villageList=new ArrayList<>();

        while(!(rs.isExhausted())){
            City_town_village village=new City_town_village();
            village=(Optional.ofNullable(rs.one().getUUID("id"))
                .map(id -> Optional.ofNullable(mapper.get(id)))
                .get()).get();
            villageList.add(village);
        }
        return villageList;

    }


    public City_town_village save(City_town_village city_town_village) {
        if (city_town_village.getId() == null) {
            city_town_village.setId(UUID.randomUUID());
        }
        mapper.save(city_town_village);

        BatchStatement batch = new BatchStatement();
        batch.add(insertByBlockStmt.bind()
            .setUUID("blockid", city_town_village.getBlockid())
            .setUUID("id", city_town_village.getId()));
        session.execute(batch);

        return city_town_village;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
