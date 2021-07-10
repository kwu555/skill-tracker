package com.example.skilltracker.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "skill")
@Data
public class SkillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
}
