package org.contributetocommunity.api.job;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "2. Job Endpoints")
public interface JobApi {

    @Operation(summary = "List all jobs", description = "List all open volunteering positions available for application")
    @ApiResponse(responseCode = "200", description = "Jobs found", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = JobDTO.class)),
            @Content(mediaType = MediaType.APPLICATION_XML_VALUE, schema = @Schema(implementation = JobDTO.class))
    })
    @ApiResponse(responseCode = "204", description = "No Jobs available")
    ResponseEntity<List<JobDTO>> getJobs();

}
