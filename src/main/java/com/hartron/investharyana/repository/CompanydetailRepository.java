package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Companydetail;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Companydetail entity.
 */
@Repository
public class CompanydetailRepository {

    private final Session session;

    private Mapper<Companydetail> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public CompanydetailRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Companydetail.class);
        this.findAllStmt = session.prepare("SELECT * FROM companydetail");
        this.truncateStmt = session.prepare("TRUNCATE companydetail");
    }

    public List<Companydetail> findAll() {
        List<Companydetail> companydetailsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Companydetail companydetail = new Companydetail();
                companydetail.setId(row.getUUID("id"));
                companydetail.setInvestorid(row.getUUID("investorid"));
                companydetail.setPromoter_md_director(row.getString("promoter_md_director"));
                companydetail.setDesignation(row.getString("designation"));
                companydetail.setBusinessentity(row.getString("businessentity"));
                companydetail.setDirector_promoter_md_ceo_number(row.getInt("director_promoter_md_ceo_number"));
                companydetail.setPan_number(row.getString("pan_number"));
                companydetail.setAadhar_number(row.getString("aadhar_number"));
                companydetail.setNri(row.getBool("nri"));
                companydetail.setTin_vat_number(row.getString("tin_vat_number"));
                companydetail.setCst_number(row.getString("cst_number"));
                companydetail.setDirector_md_ceo_list(row.getString("director_md_ceo_list"));
                companydetail.setPancard(row.getString("pancard"));
                companydetail.setAadharcard(row.getString("aadharcard"));
                companydetail.setTin_vat_document(row.getString("tin_vat_document"));
                companydetail.setCst_document(row.getString("cst_document"));
                companydetail.setMoa_partnershipdeed(row.getString("moa_partnershipdeed"));
                companydetail.setRegistration_document(row.getString("registration_document"));
                companydetail.setBusinessentitytype(row.getString("businessentitytype"));
                return companydetail;
            }
        ).forEach(companydetailsList::add);
        return companydetailsList;
    }

    public Companydetail findOne(UUID id) {
        return mapper.get(id);
    }

    public Companydetail save(Companydetail companydetail) {
        if (companydetail.getId() == null) {
            companydetail.setId(UUID.randomUUID());
        }
        mapper.save(companydetail);
        return companydetail;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
