package org.contributetocommunity.api.volunteer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.contributetocommunity.api.job.Job;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "volunteers")
@Data
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @EqualsAndHashCode.Exclude
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

}
