package org.contributetocommunity.api.volunteer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.ToString;
import org.contributetocommunity.api.job.Job;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "volunteers")
@Data
@Generated
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(max = 40)
    @Column(name = "last_name")
    private String lastName;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "jobs_volunteers",
            joinColumns = @JoinColumn(name = "volunteer_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id")
    )
    private Set<Job> jobs = new HashSet<>();


    public String getFullName() {
        return String.format("%s %s", firstName, lastName).trim();
    }

    public void addJob(Job job) {
        if (jobs == null) {
            jobs = new HashSet<>();
        }

        jobs.add(job);
    }

}
