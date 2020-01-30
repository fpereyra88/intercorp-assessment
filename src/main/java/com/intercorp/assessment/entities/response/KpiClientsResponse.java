package com.intercorp.assessment.entities.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ApiModel(description = "Average and standard deviation of clients age and ")
public class KpiClientsResponse {

    @JsonProperty("average_age")
    @ApiModelProperty(notes = "Average of clients age")
    private int averageAge;

    @JsonProperty("standard_deviation")
    @ApiModelProperty(notes = "Standard deviation of clients age")
    private double standardDeviation;
}
