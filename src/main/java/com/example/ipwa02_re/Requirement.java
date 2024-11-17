package com.example.ipwa02_re;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "requirement")
public class Requirement {
    @Id
    private Long id;

    private String description;

    private String status;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
