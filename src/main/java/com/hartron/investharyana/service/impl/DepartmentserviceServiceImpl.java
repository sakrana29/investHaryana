package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.DepartmentserviceService;
import com.hartron.investharyana.domain.Departmentservice;
import com.hartron.investharyana.repository.DepartmentserviceRepository;
import com.hartron.investharyana.service.dto.DepartmentserviceDTO;
import com.hartron.investharyana.service.mapper.DepartmentserviceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Departmentservice.
 */
@Service
public class DepartmentserviceServiceImpl implements DepartmentserviceService{

    private final Logger log = LoggerFactory.getLogger(DepartmentserviceServiceImpl.class);
    
    private final DepartmentserviceRepository departmentserviceRepository;

    private final DepartmentserviceMapper departmentserviceMapper;

    public DepartmentserviceServiceImpl(DepartmentserviceRepository departmentserviceRepository, DepartmentserviceMapper departmentserviceMapper) {
        this.departmentserviceRepository = departmentserviceRepository;
        this.departmentserviceMapper = departmentserviceMapper;
    }

    /**
     * Save a departmentservice.
     *
     * @param departmentserviceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DepartmentserviceDTO save(DepartmentserviceDTO departmentserviceDTO) {
        log.debug("Request to save Departmentservice : {}", departmentserviceDTO);
        Departmentservice departmentservice = departmentserviceMapper.departmentserviceDTOToDepartmentservice(departmentserviceDTO);
        departmentservice = departmentserviceRepository.save(departmentservice);
        DepartmentserviceDTO result = departmentserviceMapper.departmentserviceToDepartmentserviceDTO(departmentservice);
        return result;
    }

    /**
     *  Get all the departmentservices.
     *  
     *  @return the list of entities
     */
    @Override
    public List<DepartmentserviceDTO> findAll() {
        log.debug("Request to get all Departmentservices");
        List<DepartmentserviceDTO> result = departmentserviceRepository.findAll().stream()
            .map(departmentserviceMapper::departmentserviceToDepartmentserviceDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one departmentservice by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public DepartmentserviceDTO findOne(String id) {
        log.debug("Request to get Departmentservice : {}", id);
        Departmentservice departmentservice = departmentserviceRepository.findOne(UUID.fromString(id));
        DepartmentserviceDTO departmentserviceDTO = departmentserviceMapper.departmentserviceToDepartmentserviceDTO(departmentservice);
        return departmentserviceDTO;
    }

    /**
     *  Delete the  departmentservice by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Departmentservice : {}", id);
        departmentserviceRepository.delete(UUID.fromString(id));
    }
}
