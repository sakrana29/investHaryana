package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.DepartmentServiceService;
import com.hartron.investharyana.domain.DepartmentService;
import com.hartron.investharyana.repository.DepartmentServiceRepository;
import com.hartron.investharyana.service.dto.DepartmentServiceDTO;
import com.hartron.investharyana.service.mapper.DepartmentServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing DepartmentService.
 */
@Service
public class DepartmentServiceServiceImpl implements DepartmentServiceService{

    private final Logger log = LoggerFactory.getLogger(DepartmentServiceServiceImpl.class);

    private final DepartmentServiceRepository departmentServiceRepository;

    private final DepartmentServiceMapper departmentServiceMapper;

    public DepartmentServiceServiceImpl(DepartmentServiceRepository departmentServiceRepository, DepartmentServiceMapper departmentServiceMapper) {
        this.departmentServiceRepository = departmentServiceRepository;
        this.departmentServiceMapper = departmentServiceMapper;
    }

    /**
     * Save a departmentService.
     *
     * @param departmentServiceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DepartmentServiceDTO save(DepartmentServiceDTO departmentServiceDTO) {
        log.debug("Request to save DepartmentService : {}", departmentServiceDTO);
        DepartmentService departmentService = departmentServiceMapper.departmentServiceDTOToDepartmentService(departmentServiceDTO);
        departmentService = departmentServiceRepository.save(departmentService);
        DepartmentServiceDTO result = departmentServiceMapper.departmentServiceToDepartmentServiceDTO(departmentService);
        return result;
    }

    /**
     *  Get all the departmentServices.
     *
     *  @return the list of entities
     */
    @Override
    public List<DepartmentServiceDTO> findAll() {
        log.debug("Request to get all DepartmentServices");
        List<DepartmentServiceDTO> result = departmentServiceRepository.findAll().stream()
            .map(departmentServiceMapper::departmentServiceToDepartmentServiceDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one departmentService by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public DepartmentServiceDTO findOne(String id) {
        log.debug("Request to get DepartmentService : {}", id);
        DepartmentService departmentService = departmentServiceRepository.findOne(UUID.fromString(id));
        DepartmentServiceDTO departmentServiceDTO = departmentServiceMapper.departmentServiceToDepartmentServiceDTO(departmentService);
        return departmentServiceDTO;
    }

    /**
     *  Delete the  departmentService by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete DepartmentService : {}", id);
        departmentServiceRepository.delete(UUID.fromString(id));
    }

    @Override
    public List<DepartmentServiceDTO> findServiceByDepartmentId(String departmentId) {
        log.debug("Request to get DepartmentService by DepartmentId : {}", departmentId);
        List<DepartmentServiceDTO> result = departmentServiceRepository.findServicesByDepartmentId(UUID.fromString(departmentId)).stream()
            .map(departmentServiceMapper::departmentServiceToDepartmentServiceDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }
}
