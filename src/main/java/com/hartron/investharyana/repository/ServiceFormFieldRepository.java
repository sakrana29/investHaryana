package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Projectservicedetail;
import com.hartron.investharyana.domain.ServiceFormField;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Cassandra repository for the ServiceFormField entity.
 */
@Repository
public class ServiceFormFieldRepository {

    private final Session session;

    private Mapper<ServiceFormField> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;
    private PreparedStatement findAllByServiceStmt;

    private PreparedStatement insertByServiceStmt;

    private PreparedStatement deleteByServiceStmt;

    public ServiceFormFieldRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(ServiceFormField.class);
        this.findAllStmt = session.prepare("SELECT * FROM serviceFormField");
        this.truncateStmt = session.prepare("TRUNCATE serviceFormField");
        this.findAllByServiceStmt = session.prepare(
            "SELECT id " +
                "FROM serviceFormField_by_serviceid " +
                "WHERE serviceid = :serviceid");

        this.insertByServiceStmt = session.prepare(
            "INSERT INTO serviceFormField_by_serviceid (serviceid, id) " +
                "VALUES (:serviceid, :id)");

        this.deleteByServiceStmt = session.prepare(
            "DELETE FROM serviceFormField_by_serviceid " +
                "WHERE serviceid = :serviceid");
    }

    public List<ServiceFormField> findAll() {
        List<ServiceFormField> serviceFormFieldsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                ServiceFormField serviceFormField = new ServiceFormField();
                serviceFormField.setId(row.getUUID("id"));
                serviceFormField.setFieldName(row.getString("fieldName"));
                serviceFormField.setFieldType(row.getString("fieldType"));
                serviceFormField.setServiceID(row.getUUID("serviceID"));
                serviceFormField.setFieldTypeOption(row.getString("fieldTypeOption"));
                serviceFormField.setFieldRenderingOrder(row.getInt("fieldRenderingOrder"));
                return serviceFormField;
            }
        ).forEach(serviceFormFieldsList::add);
        return serviceFormFieldsList;
    }


    public List<ServiceFormField> findAllByServiceid(UUID serviceid) {
        BoundStatement stmt = findAllByServiceStmt.bind();
        stmt.setUUID("serviceid", serviceid);
        return findAllFromIndex(stmt);
    }
    private List<ServiceFormField> findAllFromIndex(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        List<ServiceFormField> serviceFormFieldArrayList=new ArrayList<>();
        while (!(rs.isExhausted())) {
            ServiceFormField serviceFormField=new ServiceFormField();
            serviceFormField= Optional.ofNullable(rs.one().getUUID("id"))
                .map(id -> Optional.ofNullable(mapper.get(id)))
                .get().get();
            serviceFormFieldArrayList.add(serviceFormField);
        }
        return serviceFormFieldArrayList;
    }

    public ServiceFormField findOne(UUID id) {
        return mapper.get(id);
    }

    public ServiceFormField save(ServiceFormField serviceFormField) {
        if (serviceFormField.getId() == null) {
            serviceFormField.setId(UUID.randomUUID());
        }
        BatchStatement batch = new BatchStatement();
        batch.add(mapper.saveQuery(serviceFormField));
        batch.add(insertByServiceStmt.bind()
            .setUUID("serviceid", serviceFormField.getServiceID())
            .setUUID("id", serviceFormField.getId()));
        session.execute(batch);
        return serviceFormField;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }

    public void deleteByProject(UUID serviceid) {
        BatchStatement batch = new BatchStatement();
        batch.add(deleteByServiceStmt.bind()
            .setUUID("serviceid", serviceid));
        session.execute(batch);
    }
}
