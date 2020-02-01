package com.intercorp.assessment.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.intercorp.assessment.entities.dto.ClientModel;
import com.intercorp.assessment.entities.response.ClientDataResponse;
import com.intercorp.assessment.entities.response.KpiClientsResponse;
import com.intercorp.assessment.services.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * Main controller of the Intercorp Clients Management API.
 *
 * @author Federico Pereyra
 */

@RestController
@CrossOrigin(allowedHeaders = "*", maxAge = 3600, allowCredentials = "true")
@RequestMapping("/api/v1")
@Api(value = "Clients management", description = "Basic operations with clients model")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("/creacliente")
    @ApiOperation(value = "Create a new client",
            response = String.class)
    public String createClient(@ApiParam(value = "Client object store in database", required = true)
                                   @RequestBody ClientModel clientModel) throws JsonProcessingException {
        return clientService.createClient(clientModel);
    }

    @GetMapping("/kpideclientes")
    @ApiOperation(value = "Return the average age of clients and the standard deviation",
            response = KpiClientsResponse.class)
    public KpiClientsResponse getClientsKpi() throws JsonProcessingException, ParseException {
        return clientService.getKpiClients();
    }

    @GetMapping("/listclientes")
    @ApiOperation(value = "Get clients information with date of dead", response = ClientDataResponse.class,
            responseContainer = "List")
    public List<ClientDataResponse> listClients() throws JsonProcessingException, ParseException {
        return clientService.getClientsData();
    }
}
