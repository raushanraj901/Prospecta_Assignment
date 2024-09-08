package com.prospecta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.prospecta.service.CsvService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/api/csv")
public class CsvController {

    @Autowired
    private CsvService csvFormulaProcessor;

    @PostMapping(value = "/upload-csv", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return new ResponseEntity<>("File is empty.", HttpStatus.BAD_REQUEST);
            }

            if (!file.getOriginalFilename().endsWith(".csv") || 
                !file.getContentType().equals("text/csv")) {
                return new ResponseEntity<>("File must be a CSV type.", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String processedCSV = csvFormulaProcessor.processCSV(reader);

            return new ResponseEntity<>(processedCSV, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to process CSV: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
