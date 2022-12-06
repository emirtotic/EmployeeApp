package com.employee.app.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "employee", schema = "employee-tracker")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "personal_id")
    private Long personalId;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "team")
    private String team;

    @NotNull
    @Column(name = "team_lead")
    private String teamLead;
}
