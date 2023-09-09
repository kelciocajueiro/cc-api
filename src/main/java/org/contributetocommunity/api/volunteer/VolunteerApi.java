package org.contributetocommunity.api.volunteer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "3. Volunteer Endpoints")
public interface VolunteerApi {

    @Operation(summary = "List all volunteers", description = "List all volunteers that can apply for an open position")
    @ApiResponse(responseCode = "200", description = "Volunteers found", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = VolunteerDTO.class)),
            @Content(mediaType = MediaType.APPLICATION_XML_VALUE, schema = @Schema(implementation = VolunteerDTO.class))
    })
    @ApiResponse(responseCode = "204", description = "No Volunteers found")
    ResponseEntity<List<VolunteerDTO>> getVolunteers();

    @Operation(summary = "List the volunteers applied for a Job", description = "List the volunteers that applied for a specific Job")
    @ApiResponse(responseCode = "200", description = "Volunteers found", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = VolunteerDTO.class)),
            @Content(mediaType = MediaType.APPLICATION_XML_VALUE, schema = @Schema(implementation = VolunteerDTO.class))
    })
    @ApiResponse(responseCode = "204", description = "No Volunteers found")
    ResponseEntity<List<VolunteerDTO>> getVolunteersByJobId(@Parameter(description = "Job ID") long jobId);

    @Operation(summary = "List the volunteers with the position details"
            , description = "A paginated list of volunteers with the position details they applied for")
    @ApiResponse(responseCode = "200", description = "Volunteers found", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = VolunteerDTO.class)),
            @Content(mediaType = MediaType.APPLICATION_XML_VALUE, schema = @Schema(implementation = VolunteerDTO.class))
    })
    @ApiResponse(responseCode = "204", description = "No Volunteers found")
    Page<VolunteerJobsDTO> getVolunteersWithJobs(@ParameterObject Pageable pageable);

}
