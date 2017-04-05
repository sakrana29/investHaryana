package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.FiletestingService;
import com.hartron.investharyana.domain.Filetesting;
import com.hartron.investharyana.repository.FiletestingRepository;
import com.hartron.investharyana.service.dto.FiletestingDTO;
import com.hartron.investharyana.service.mapper.FiletestingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Filetesting.
 */
@Service
public class FiletestingServiceImpl implements FiletestingService{

    private final Logger log = LoggerFactory.getLogger(FiletestingServiceImpl.class);
    
    private final FiletestingRepository filetestingRepository;

    private final FiletestingMapper filetestingMapper;

    public FiletestingServiceImpl(FiletestingRepository filetestingRepository, FiletestingMapper filetestingMapper) {
        this.filetestingRepository = filetestingRepository;
        this.filetestingMapper = filetestingMapper;
    }

    /**
     * Save a filetesting.
     *
     * @param filetestingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FiletestingDTO save(FiletestingDTO filetestingDTO) {
        log.debug("Request to save Filetesting : {}", filetestingDTO);
        Filetesting filetesting = filetestingMapper.filetestingDTOToFiletesting(filetestingDTO);
        filetesting = filetestingRepository.save(filetesting);
        FiletestingDTO result = filetestingMapper.filetestingToFiletestingDTO(filetesting);
        return result;
    }

    /**
     *  Get all the filetestings.
     *  
     *  @return the list of entities
     */
    @Override
    public List<FiletestingDTO> findAll() {
        log.debug("Request to get all Filetestings");
        List<FiletestingDTO> result = filetestingRepository.findAll().stream()
            .map(filetestingMapper::filetestingToFiletestingDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one filetesting by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public FiletestingDTO findOne(String id) {
        log.debug("Request to get Filetesting : {}", id);
        Filetesting filetesting = filetestingRepository.findOne(UUID.fromString(id));
        FiletestingDTO filetestingDTO = filetestingMapper.filetestingToFiletestingDTO(filetesting);
        return filetestingDTO;
    }

    /**
     *  Delete the  filetesting by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Filetesting : {}", id);
        filetestingRepository.delete(UUID.fromString(id));
    }
}
