package com.hartron.investharyana.web.rest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hartron.investharyana.service.FileManagementService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;

/**
 * REST controller for managing Block.
 */
@RestController
@RequestMapping("/api")
public class FileManagementResource {

    private final FileManagementService fileManagementService;

    public FileManagementResource(FileManagementService fileManagementService) {
        this.fileManagementService = fileManagementService;
    }

    @RequestMapping(value = "/FileManagement", method = RequestMethod.POST)
    @ResponseBody
    public Object saveFile(@RequestParam(value = "filename") String filename, @RequestParam(value = "file") MultipartFile file,HttpServletRequest request) {

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(filename);
        try {
            fileManagementService.storeFile(file,filename);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/FileManagementGetFile/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileManagementService.loadFile(filename);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
            .body(file);
    }


}
