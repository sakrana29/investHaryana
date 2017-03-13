package com.hartron.investharyana.repository;

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

    private PreparedStatement findServiceFormFieldsByServiceStmt;

    private PreparedStatement insertByServiceStmt;

    private PreparedStatement truncateStmt;

    public ServiceFormFieldRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(ServiceFormField.class);
        this.findAllStmt = session.prepare("SELECT * FROM serviceFormField");
        this.truncateStmt = session.prepare("TRUNCATE serviceFormField");

        findServiceFormFieldsByServiceStmt = session.prepare(
            "SELECT id " +
                "FROM serviceFormField_by_service " +
                "WHERE serviceID = :serviceID");

        insertByServiceStmt = session.prepare(
            "INSERT INTO serviceFormField_by_service (serviceID, id) " +
                "VALUES (:serviceID, :id)");
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
                return serviceFormField;
            }
        ).forEach(serviceFormFieldsList::add);
        return serviceFormFieldsList;
    }

    public ServiceFormField findOne(UUID id) {
        return mapper.get(id);
    }

    public ServiceFormField save(ServiceFormField serviceFormField) {
        UUID serviceFormFieldId= UUID.randomUUID();
        if (serviceFormField.getId() == null) {
            serviceFormField.setId(serviceFormFieldId);
        }
        mapper.save(serviceFormField);

        BatchStatement batch = new BatchStatement();
        batch.add(insertByServiceStmt.bind()
            .setUUID("serviceID", serviceFormField.getServiceID())
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

    public List<ServiceFormField> findServiceFormFieldsByService(UUID serviceid) {
        BoundStatement stmt = findServiceFormFieldsByServiceStmt.bind();
        stmt.setUUID("serviceID", serviceid);
        return findServiceFormFieldsFromIndex(stmt);
    }

    private List<ServiceFormField> findServiceFormFieldsFromIndex(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        List<ServiceFormField> ServiceFormFieldList=new ArrayList<>();

        while(!(rs.isExhausted())){
            ServiceFormField service=new ServiceFormField();
            service=(Optional.ofNullable(rs.one().getUUID("id"))
                .map(id -> Optional.ofNullable(mapper.get(id)))
                .get()).get();
            ServiceFormFieldList.add(service);
        }
        return ServiceFormFieldList;

    }
}
