package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Electricrequirement;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Electricrequirement entity.
 */
@Repository
public class ElectricrequirementRepository {

    private final Session session;

    private Mapper<Electricrequirement> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ElectricrequirementRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Electricrequirement.class);
        this.findAllStmt = session.prepare("SELECT * FROM electricrequirement");
        this.truncateStmt = session.prepare("TRUNCATE electricrequirement");
    }

    public List<Electricrequirement> findAll() {
        List<Electricrequirement> electricrequirementsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Electricrequirement electricrequirement = new Electricrequirement();
                electricrequirement.setId(row.getUUID("id"));
                electricrequirement.setProjectid(row.getUUID("projectid"));
                electricrequirement.setTemporaryrequired(row.getBool("temporaryrequired"));
                electricrequirement.setTem_load_existing(row.getBool("tem_load_existing"));
                electricrequirement.setTem_account_number(row.getString("tem_account_number"));
                electricrequirement.setTemp_existing_load_demand_kw(row.getDecimal("temp_existing_load_demand_kw"));
                electricrequirement.setTemp_existing_load_demand_kva(row.getDecimal("temp_existing_load_demand_kva"));
                electricrequirement.setTemp_new_load_demand_kw(row.getDecimal("temp_new_load_demand_kw"));
                electricrequirement.setTemp_new_load_demand_kva(row.getDecimal("temp_new_load_demand_kva"));
                electricrequirement.setTemp_load_demand_date(row.get("temp_load_demand_date", ZonedDateTime.class));
                electricrequirement.setRegular_load_required(row.getBool("regular_load_required"));
                electricrequirement.setRegular_existing_connection(row.getBool("regular_existing_connection"));
                electricrequirement.setCustomertype(row.getUUID("customertype"));
                electricrequirement.setRegular_account_number(row.getString("regular_account_number"));
                electricrequirement.setRegular_existing_load_ifany_kw(row.getDecimal("regular_existing_load_ifany_kw"));
                electricrequirement.setRegular_existing_load_ifany_kva(row.getDecimal("regular_existing_load_ifany_kva"));
                electricrequirement.setRegular_new_load_demand_kw(row.getDecimal("regular_new_load_demand_kw"));
                electricrequirement.setRegular_new_load_demand_kva(row.getDecimal("regular_new_load_demand_kva"));
                electricrequirement.setRegular_load_demand_date(row.get("regular_load_demand_date", ZonedDateTime.class));
                electricrequirement.setTemporaryconnection(row.getString("temporaryconnection"));
                electricrequirement.setRegular_connection_doc(row.getString("regular_connection_doc"));
                return electricrequirement;
            }
        ).forEach(electricrequirementsList::add);
        return electricrequirementsList;
    }

    public Electricrequirement findOne(UUID id) {
        return mapper.get(id);
    }

    public Electricrequirement save(Electricrequirement electricrequirement) {
        if (electricrequirement.getId() == null) {
            electricrequirement.setId(UUID.randomUUID());
        }
        mapper.save(electricrequirement);
        return electricrequirement;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
