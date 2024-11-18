package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "planet")
public class Planet {
    @Id
    @Pattern(regexp = "^[A-Z] + $", message = " ID must contains only uppercase Latin letters")
    private String id;

    @Column(length = 500, nullable = false)
    @Size(min = 1, max = 500)
    private String name;
}
