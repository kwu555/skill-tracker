package com.example.skilltracker.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "member_skills")
@Data
public class MemberSkillsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @OneToOne(optional = false)
    @JoinColumn(name = "skill_id", referencedColumnName = "ID")
    private SkillEntity skill;

    @Column(name = "skill_level")
    private Integer skillLevel;
}
