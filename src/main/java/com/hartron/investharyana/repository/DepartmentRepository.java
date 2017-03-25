package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Department;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Department entity.
 */
@Repository
public class DepartmentRepository {

    private final Session session;

    private Mapper<Department> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public DepartmentRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Department.class);
        this.findAllStmt = session.prepare("SELECT * FROM department");
        this.truncateStmt = session.prepare("TRUNCATE department");
    }

    public List<Department> findAll() {
        List<Department> departmentsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Department department = new Department();
                department.setId(row.getUUID("id"));
                department.setDepartmentname(row.getString("departmentname"));
                department.setDescription(row.getString("description"));
                department.setHod(row.getString("hod"));
                department.setEmail(row.getString("email"));
                department.setHodmobile(row.getString("hodmobile"));
                return department;
            }
        ).forEach(departmentsList::add);
        return departmentsList;
    }

    public Department findOne(UUID id) {
        return mapper.get(id);
    }

    public Department save(Department department) {
        if (department.getId() == null) {
            department.setId(UUID.randomUUID());
        }
        mapper.save(department);
        return department;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
