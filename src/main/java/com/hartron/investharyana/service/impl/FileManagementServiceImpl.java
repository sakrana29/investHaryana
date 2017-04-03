package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.domain.Block;
import com.hartron.investharyana.repository.BlockRepository;
import com.hartron.investharyana.service.BlockService;
import com.hartron.investharyana.service.FileManagementService;
import com.hartron.investharyana.service.dto.BlockDTO;
import com.hartron.investharyana.service.mapper.BlockMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Block.
 */
@Service
public class FileManagementServiceImpl implements FileManagementService{

    private final Logger log = LoggerFactory.getLogger(FileManagementServiceImpl.class);

    private final Path rootLocation = Paths.get("uploads");

    public void storeFile(MultipartFile file, String filename){
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename));
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }


}
