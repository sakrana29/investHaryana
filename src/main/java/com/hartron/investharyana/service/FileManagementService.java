package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.BlockDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Service Interface for File Management.
 */
public interface FileManagementService {

    void storeFile(MultipartFile file, String filename);
}
