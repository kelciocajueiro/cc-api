package org.contributetocommunity.api.dataload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/v1/data-load")
public class DataLoadController implements DataLoadApi {

    private final DataLoadService dataLoadService;

    @Autowired
    public DataLoadController(DataLoadService dataLoadService) {
        this.dataLoadService = dataLoadService;
    }

    @GetMapping
    public ResponseEntity<String> loadData() {
        try {
            dataLoadService.loadDataFromSpreadsheet();
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while processing the load data.");
        }

        return ResponseEntity.ok("Data loaded and saved successfully.");
    }

}
