package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Investor;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.hartron.investharyana.security.SecurityUtils;
import org.springframework.stereotype.Repository;

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

    private PreparedStatement insertByUserloginStmt;
    private PreparedStatement findByUserloginStmt;

    public InvestorRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Investor.class);
        this.findAllStmt = session.prepare("SELECT * FROM investor");
        this.truncateStmt = session.prepare("TRUNCATE investor");

        this.insertByUserloginStmt = session.prepare(
            "INSERT INTO investor_by_userlogin (userlogin, id) " +
                "VALUES (:userlogin, :id)");

        this.findByUserloginStmt = session.prepare(
            "SELECT id " +
                "FROM investor_by_userlogin " +
                "WHERE userlogin = :userlogin");
    }

    public List<Investor> findInvestorbyUserLogin() {
        BoundStatement stmt = findByUserloginStmt.bind();
        stmt.setString("userlogin", SecurityUtils.getCurrentUserLogin());
        return findInvestorFromIndex(stmt);
    }

    private List<Investor> findInvestorFromIndex(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        List<Investor> investorList=new ArrayList<>();

        while(!(rs.isExhausted())){
            Investor investor=new Investor();
            investor=(Optional.ofNullable(rs.one().getUUID("id"))
                .map(id -> Optional.ofNullable(mapper.get(id)))
                .get()).get();
            investorList.add(investor);
        }
        return investorList;
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
                investor.setCountryid(row.getUUID("countryid"));
                investor.setStateid(row.getUUID("stateid"));
                investor.setCityid(row.getUUID("cityid"));
                investor.setAddress1(row.getString("address1"));
                investor.setAddress2(row.getString("address2"));
                investor.setAddress3(row.getString("address3"));
                investor.setEmailprimary(row.getString("emailprimary"));
                investor.setEmailsecondary(row.getString("emailsecondary"));
                investor.setMoudocument(row.getString("moudocument"));
                investor.setInvestorpicpath(row.getString("investorpicpath"));
                investor.setUserlogin(row.getString("userlogin"));
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
            investor.setUserlogin(SecurityUtils.getCurrentUserLogin());
        }
        mapper.save(investor);

        BatchStatement batch = new BatchStatement();
        batch.add(insertByUserloginStmt.bind()
            .setString("userlogin", SecurityUtils.getCurrentUserLogin())
            .setUUID("id", investor.getId()));
        session.execute(batch);

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
