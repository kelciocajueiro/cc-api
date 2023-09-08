package org.contributetocommunity.api.dataload;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "1. Data Load Endpoints")
public interface DataLoadApi {

    @Operation(summary = "Load database", description = "Import data from local spreadsheet and save to database")
    @ApiResponse(responseCode = "200", description = "Data loaded and saved", content = {
            @Content(schema = @Schema(implementation = String.class))
    })
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    ResponseEntity<String> loadData();

}
