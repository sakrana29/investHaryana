package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.ServiceFormField;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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

    public ServiceFormFieldRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(ServiceFormField.class);
        this.findAllStmt = session.prepare("SELECT * FROM serviceFormField");
        this.truncateStmt = session.prepare("TRUNCATE serviceFormField");
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

    public ServiceFormField findOne(UUID id) {
        return mapper.get(id);
    }

    public ServiceFormField save(ServiceFormField serviceFormField) {
        if (serviceFormField.getId() == null) {
            serviceFormField.setId(UUID.randomUUID());
        }
        mapper.save(serviceFormField);
        return serviceFormField;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
