package com.prospecta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.prospecta.exception.CsvProcessingException;
import com.prospecta.service.CsvService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/csv")
public class CsvController {

    @Autowired
    private CsvService csvService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadCsv(@RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            ByteArrayInputStream processedCsv = csvService.processCsv(inputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=processed.csv");
            headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(new InputStreamResource(processedCsv));
        } catch (CsvProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error processing CSV file: " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }
}
