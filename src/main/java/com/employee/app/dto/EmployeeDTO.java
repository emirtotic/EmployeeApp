package com.employee.app.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDTO {

    private Long id;

    @NotNull
    private Long personalId;

    @NotEmpty
    @Size(min = 3)
    private String name;

    @NotEmpty
    @Size(min = 3)
    private String team;

    @NotEmpty
    @Size(min = 3)
    private String teamLead;
}
