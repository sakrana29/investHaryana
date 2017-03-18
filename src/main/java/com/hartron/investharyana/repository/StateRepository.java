package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.State;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Cassandra repository for the State entity.
 */
@Repository
public class StateRepository {

    private final Session session;

    private Mapper<State> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;
    private PreparedStatement insertByCountryStmt;
    private PreparedStatement findByCountryStmt;

    public StateRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(State.class);
        this.findAllStmt = session.prepare("SELECT * FROM state");
        this.truncateStmt = session.prepare("TRUNCATE state");
        this.insertByCountryStmt = session.prepare(
            "INSERT INTO state_by_country (countryid, id) " +
                "VALUES (:countryid, :id)");

        this.findByCountryStmt = session.prepare(
            "SELECT id " +
                "FROM state_by_country " +
                "WHERE countryid = :countryid");
    }

    public List<State> findStatebycountryId(UUID countryid) {
        BoundStatement stmt = findByCountryStmt.bind();
        stmt.setUUID("countryid", countryid);
        return findstateFromIndex(stmt);
    }

    private List<State> findstateFromIndex(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        List<State> stateList=new ArrayList<>();

        while(!(rs.isExhausted())){
            State state=new State();
            state=(Optional.ofNullable(rs.one().getUUID("id"))
                .map(id -> Optional.ofNullable(mapper.get(id)))
                .get()).get();
            stateList.add(state);
        }
        return stateList;

    }

    public List<State> findAll() {
        List<State> statesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                State state = new State();
                state.setId(row.getUUID("id"));
                state.setCountryid(row.getUUID("countryid"));
                state.setStatename(row.getString("statename"));
                return state;
            }
        ).forEach(statesList::add);
        return statesList;
    }

    public State findOne(UUID id) {
        return mapper.get(id);
    }

    public State save(State state) {
        if (state.getId() == null) {
            state.setId(UUID.randomUUID());
        }
        mapper.save(state);
        BatchStatement batch = new BatchStatement();
        batch.add(insertByCountryStmt.bind()
            .setUUID("countryid", state.getCountryid())
            .setUUID("id", state.getId()));
        session.execute(batch);
        return state;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }


}
