package org.contributetocommunity.api.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    @Modifying
    @Query(value = "insert into public.jobs_volunteers (job_id, volunteer_id) values (:jobId, :volunteerId)", nativeQuery = true)
    void insertJobVolunteerRelationship(Long jobId, Long volunteerId);

}
