package com.intercorp.assessment.entities.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intercorp.assessment.entities.dto.ClientModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@ApiModel(description = "Client information and estimated date of dead")
public class ClientDataResponse {

    @JsonProperty("client")
    @ApiModelProperty(notes = "Client data")
    private ClientModel clientModel;

    @JsonProperty("dead_date")
    @ApiModelProperty(notes = "Estimated client date of dead")
    private Date deadDate;
}
