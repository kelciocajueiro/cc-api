package org.contributetocommunity.api.job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class JobDTO {

    private Long id;

    private String name;

    private String description;

}
