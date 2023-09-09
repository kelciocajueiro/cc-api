package org.contributetocommunity.api.job;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.ToString;
import org.contributetocommunity.api.volunteer.Volunteer;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jobs")
@Data
@Generated
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    @Column(name = "job_name", nullable = false)
    private String name;

    @Size(max = 1000)
    @Column(name = "job_description", nullable = false, length = 1000)
    private String description;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "jobs")
    private Set<Volunteer> volunteers = new HashSet<>();

}

