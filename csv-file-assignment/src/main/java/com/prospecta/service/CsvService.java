package com.prospecta.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.prospecta.exception.CsvProcessingException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    public ByteArrayInputStream processCsv(InputStream inputStream) {
        List<String[]> processedData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

            for (CSVRecord record : records) {
                String[] processedRow = new String[record.size()];

                for (int i = 0; i < record.size(); i++) {
                    String cell = record.get(i);

                    String processedCell = processCell(cell);

                    processedRow[i] = processedCell;
                }
                processedData.add(processedRow);
            }
        } catch (IOException e) {
            throw new CsvProcessingException("Failed to process CSV file", e);
        }

        try {
            return convertToCsv(processedData);
        } catch (IOException e) {
            throw new CsvProcessingException("Failed to convert processed data to CSV", e);
        }
    }

    private String processCell(String cell) {
        return cell;
    }

    private ByteArrayInputStream convertToCsv(List<String[]> data) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream))) {
            for (String[] row : data) {
                writer.println(String.join(",", row));
            }
        }
        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
