package com.intercorp.assessment.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Information about client")
public class ClientModel {

    @JsonProperty("name")
    @ApiModelProperty(notes = "The client name")
    private String name;

    @JsonProperty("surname")
    @ApiModelProperty(notes = "The client surname")
    private String surname;

    @JsonProperty("age")
    @ApiModelProperty(notes = "The client age")
    private int age;

    @JsonProperty("birthdate")
    @ApiModelProperty(notes = "The client birth date")
    private Date birthdate;
}
