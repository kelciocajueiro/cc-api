package org.contributetocommunity.api.volunteer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    @Query("select v from Volunteer v join fetch v.jobs j where j.id = :jobId")
    List<Volunteer> findByJobId(@Param("jobId") Long jobId);

    @Query(value = "select new org.contributetocommunity.api.volunteer.VolunteerJobDetailsDTO" +
            "(v.id, v.firstName, v.lastName, j.name, j.description) " +
            "from Volunteer v join v.jobs j",
            countQuery = "select count(v) from Volunteer v")
    Page<VolunteerJobDetailsDTO> findWithPositionDetails(Pageable pageable);

}
