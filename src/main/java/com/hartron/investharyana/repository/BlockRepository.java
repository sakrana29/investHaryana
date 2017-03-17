package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Block;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Cassandra repository for the Block entity.
 */
@Repository
public class BlockRepository {

    private final Session session;

    private Mapper<Block> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    private PreparedStatement insertByDistrictStmt;

    private PreparedStatement findByDistrictStmt;

    public BlockRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Block.class);
        this.findAllStmt = session.prepare("SELECT * FROM block");
        this.truncateStmt = session.prepare("TRUNCATE block");

        this.insertByDistrictStmt = session.prepare(
            "INSERT INTO block_by_district (districtid, id) " +
                "VALUES (:districtid, :id)");

        this.findByDistrictStmt = session.prepare(
            "SELECT id " +
                "FROM block_by_district " +
                "WHERE districtid = :districtid");
    }

    public List<Block> findAll() {
        List<Block> blocksList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Block block = new Block();
                block.setId(row.getUUID("id"));
                block.setDistrictid(row.getUUID("districtid"));
                block.setBlockname(row.getString("blockname"));
                return block;
            }
        ).forEach(blocksList::add);
        return blocksList;
    }

    public Block findOne(UUID id) {
        return mapper.get(id);
    }

    public List<Block> findBlockByDistrictId(UUID districtid) {
        BoundStatement stmt = findByDistrictStmt.bind();
        stmt.setUUID("districtid", districtid);
        return findBlockFromIndex(stmt);
    }

    private List<Block> findBlockFromIndex(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        List<Block> blockList=new ArrayList<>();

        while(!(rs.isExhausted())){
            Block block=new Block();
            block=(Optional.ofNullable(rs.one().getUUID("id"))
                .map(id -> Optional.ofNullable(mapper.get(id)))
                .get()).get();
            blockList.add(block);
        }
        return blockList;

    }

    public Block save(Block block) {
        if (block.getId() == null) {
            block.setId(UUID.randomUUID());
        }
        mapper.save(block);

        BatchStatement batch = new BatchStatement();
        batch.add(insertByDistrictStmt.bind()
            .setUUID("districtid", block.getDistrictid())
            .setUUID("id", block.getId()));
        session.execute(batch);

        return block;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
