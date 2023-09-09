package org.contributetocommunity.api.dataload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class DataLoadDTO {

    private Long jobId;

    private Long volunteerId;

}
