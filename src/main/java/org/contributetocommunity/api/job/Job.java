package org.contributetocommunity.api.job;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.contributetocommunity.api.volunteer.Volunteer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jobs")
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_name", nullable = false)
    private String name;

    @Column(name = "job_description", nullable = false, length = 1000)
    private String description;

    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "jobs")
    private Set<Volunteer> volunteers = new HashSet<>();

}

