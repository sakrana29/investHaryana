package com.hartron.investharyana.repository;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.hartron.investharyana.domain.Investor;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Cassandra repository for the Investor entity.
 */
@Repository
public class InvestorRepository {

    private final Session session;

    private Mapper<Investor> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    private PreparedStatement findAllByCafPinStmt;

    private PreparedStatement insertByCafPinStmt;


    public InvestorRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Investor.class);
        this.findAllStmt = session.prepare("SELECT * FROM investor");
        this.truncateStmt = session.prepare("TRUNCATE investor");
        this.findAllByCafPinStmt = session.prepare(
            "SELECT id " +
                "FROM investor_by_cafpin " +
                "WHERE cafpin = :cafpin");

        this.insertByCafPinStmt = session.prepare(
            "INSERT INTO investor_by_cafpin (cafpin, id) " +
                "VALUES (:cafpin, :id)");
    }

    public List<Investor> findAllByCafpin(Double cafpin) {
        BoundStatement stmt = findAllByCafPinStmt.bind();
        stmt.setDouble("cafpin", cafpin);
        return findOneFromIndexByCafPin(stmt);
    }

    private List<Investor> findOneFromIndexByCafPin(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        List<Investor> investorListList=new ArrayList<>();
        while (!(rs.isExhausted())) {
            Investor investor=new Investor();
            investor= Optional.ofNullable(rs.one().getUUID("id"))
                .map(id -> Optional.ofNullable(mapper.get(id)))
                .get().get();
            investorListList.add(investor);
        }
        return investorListList;
    }
    public List<Investor> findAll() {
        List<Investor> investorsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Investor investor = new Investor();
                investor.setId(row.getUUID("id"));
                investor.setMouapplicable(row.getBool("mouapplicable"));
                investor.setMousignyear(row.getInt("mousignyear"));
                investor.setMouidnumber(row.getString("mouidnumber"));
                investor.setFirstname(row.getString("firstname"));
                investor.setMiddlename(row.getString("middlename"));
                investor.setLastname(row.getString("lastname"));
                investor.setAddress1(row.getString("address1"));
                investor.setAddress2(row.getString("address2"));
                investor.setAddress3(row.getString("address3"));
                investor.setEmailprimary(row.getString("emailprimary"));
                investor.setEmailsecondary(row.getString("emailsecondary"));
                investor.setUserlogin(row.getString("userlogin"));
                investor.setCityname(row.getString("cityname"));
                investor.setCountryname(row.getString("countryname"));
                investor.setStatename(row.getString("statename"));
                investor.setPincode(row.getInt("pincode"));
                investor.setPhonenumber(row.getDouble("phonenumber"));
                investor.setMobilenumber(row.getDouble("mobilenumber"));
                investor.setCafpin(row.getDouble("cafpin"));
                investor.setCreatedate(row.get("createdate", ZonedDateTime.class));
                investor.setUpdatedate(row.get("updatedate", ZonedDateTime.class));
                return investor;
            }
        ).forEach(investorsList::add);
        return investorsList;
    }

    public Investor findOne(UUID id) {
        return mapper.get(id);
    }


    public Investor save(Investor investor) {
        if (investor.getId() == null) {
            investor.setId(UUID.randomUUID());
        }

        BatchStatement batch = new BatchStatement();
        batch.add(mapper.saveQuery(investor));
        batch.add(insertByCafPinStmt.bind()
                .setDouble("cafpin", investor.getCafpin())
                .setUUID("id", investor.getId()));
        session.execute(batch);

        //mapper.save(investor);
        return investor;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
