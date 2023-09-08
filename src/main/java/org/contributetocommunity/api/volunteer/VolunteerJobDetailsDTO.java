package org.contributetocommunity.api.volunteer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class VolunteerJobDetailsDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String jobName;

    private String jobDescription;

}
