package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ServiceFormFieldService;
import com.hartron.investharyana.domain.ServiceFormField;
import com.hartron.investharyana.repository.ServiceFormFieldRepository;
import com.hartron.investharyana.service.dto.ServiceFormFieldDTO;
import com.hartron.investharyana.service.mapper.ServiceFormFieldMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ServiceFormField.
 */
@Service
public class ServiceFormFieldServiceImpl implements ServiceFormFieldService{

    private final Logger log = LoggerFactory.getLogger(ServiceFormFieldServiceImpl.class);

    private final ServiceFormFieldRepository serviceFormFieldRepository;

    private final ServiceFormFieldMapper serviceFormFieldMapper;

    public ServiceFormFieldServiceImpl(ServiceFormFieldRepository serviceFormFieldRepository, ServiceFormFieldMapper serviceFormFieldMapper) {
        this.serviceFormFieldRepository = serviceFormFieldRepository;
        this.serviceFormFieldMapper = serviceFormFieldMapper;
    }

    /**
     * Save a serviceFormField.
     *
     * @param serviceFormFieldDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ServiceFormFieldDTO save(ServiceFormFieldDTO serviceFormFieldDTO) {
        log.debug("Request to save ServiceFormField : {}", serviceFormFieldDTO);
        ServiceFormField serviceFormField = serviceFormFieldMapper.serviceFormFieldDTOToServiceFormField(serviceFormFieldDTO);
        serviceFormField = serviceFormFieldRepository.save(serviceFormField);
        ServiceFormFieldDTO result = serviceFormFieldMapper.serviceFormFieldToServiceFormFieldDTO(serviceFormField);
        return result;
    }

    /**
     *  Get all the serviceFormFields.
     *
     *  @return the list of entities
     */
    @Override
    public List<ServiceFormFieldDTO> findAll() {
        log.debug("Request to get all ServiceFormFields");
        List<ServiceFormFieldDTO> result = serviceFormFieldRepository.findAll().stream()
            .map(serviceFormFieldMapper::serviceFormFieldToServiceFormFieldDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one serviceFormField by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ServiceFormFieldDTO findOne(String id) {
        log.debug("Request to get ServiceFormField : {}", id);
        ServiceFormField serviceFormField = serviceFormFieldRepository.findOne(UUID.fromString(id));
        ServiceFormFieldDTO serviceFormFieldDTO = serviceFormFieldMapper.serviceFormFieldToServiceFormFieldDTO(serviceFormField);
        return serviceFormFieldDTO;
    }

    /**
     *  Delete the  serviceFormField by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete ServiceFormField : {}", id);
        serviceFormFieldRepository.delete(UUID.fromString(id));
    }
    @Override
    public List<ServiceFormFieldDTO> findAllByServiceid(String serviceid) {
        log.debug("Request to get all serviceformfield by serviceid : {}", serviceid);
        List<ServiceFormFieldDTO> result = serviceFormFieldRepository.findAllByServiceid(UUID.fromString(serviceid)).stream()
            .map(serviceFormFieldMapper::serviceFormFieldToServiceFormFieldDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }
}
