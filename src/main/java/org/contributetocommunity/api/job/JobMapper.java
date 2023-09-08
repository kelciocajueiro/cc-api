package org.contributetocommunity.api.job;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper {

    JobDTO toDTO(Job job);
    
    Job toEntity(JobDTO jobDTO);

}
