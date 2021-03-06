package com.intercorp.assessment.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.intercorp.assessment.client.RestClient;
import com.intercorp.assessment.entities.aws.ForDynamoDB;
import com.intercorp.assessment.entities.builders.DynamoBuilder;
import com.intercorp.assessment.entities.dto.ClientModel;
import com.intercorp.assessment.entities.response.ClientDataResponse;
import com.intercorp.assessment.entities.response.KpiClientsResponse;
import com.intercorp.assessment.utils.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class ClientService {

    private static final String URL_BASE = "https://c6y0ar0szd.execute-api.us-east-1.amazonaws.com/prod/client";

    @Autowired
    RestClient restClient;

    /**
     * Create a new client into de database
     *
     * @param client
     * @return
     * @throws JsonProcessingException
     */
    public String createClient(ClientModel client) throws JsonProcessingException {

        validateClient(client);

        try {
            ForDynamoDB forDynamoDB = new ForDynamoDB("ClientModelDB", "clientId");
            return restClient.request(URL_BASE, DynamoBuilder.saveObject(client, forDynamoDB), HttpMethod.POST, String.class);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Get the average and standard deviation of the ages of clients
     *
     * @return
     * * @throws JsonProcessingException
     */
    public KpiClientsResponse getKpiClients() throws JsonProcessingException, ParseException {
        List<ClientModel> clients = getClientsFromDB();

        List<Integer> ages = getAges(clients);

        int averageAge = MathUtils.calculateAgeAverage(ages);

        double standardDeviation = MathUtils.calculateStandardDeviation(ages);

        return KpiClientsResponse.builder()
                .averageAge(averageAge)
                .standardDeviation(standardDeviation)
                .build();
    }

    /**
     * Get the clients information with estimated date of dead
     *
     * @return
     * * @throws JsonProcessingException
     */
    public List<ClientDataResponse> getClientsData() throws JsonProcessingException, ParseException {
        List<ClientModel> clients = getClientsFromDB();
        int ageAverage = MathUtils.calculateAgeAverage(getAges(clients));

        List<ClientDataResponse> response = new ArrayList<>();
        for (ClientModel client : clients) {
            response.add(ClientDataResponse.builder()
                    .clientModel(client)
                    .deathDate(getEstimatedDeathDate(client.getBirthdate(), ageAverage))
                    .build());
        }

        return response;
    }

    /**
     * Calculate estimated death date
     *
     * @param birthDate
     * @param averageAge
     * @return
     */
    private Date getEstimatedDeathDate(Date birthDate, int averageAge) {
        return MathUtils.estimatedDeathDate(birthDate, averageAge);
    }

    /**
     *
     * @return
     * @throws JsonProcessingException
     * @throws ParseException
     */
    private List<ClientModel> getClientsFromDB() throws JsonProcessingException, ParseException {
        ForDynamoDB forDynamoDB = new ForDynamoDB("ClientModelDB", "clientId");
        List<LinkedHashMap> clientsFromDB = restClient.request(DynamoBuilder.getAllObject(forDynamoDB, URL_BASE + "/all"), HttpMethod.GET, List.class);
        return parseClientModelFromDB(clientsFromDB);
    }

    /**
     *
     * @param clientsFromDB
     * @return
     * @throws ParseException
     */
    private List<ClientModel> parseClientModelFromDB(List<LinkedHashMap> clientsFromDB) throws ParseException {
        List<ClientModel> clients = new ArrayList<>();
        for (LinkedHashMap client : clientsFromDB) {
            clients.add(ClientModel.builder()
                    .name(client.get("name").toString())
                    .surname(client.get("surname").toString())
                    .age(Integer.valueOf(client.get("age").toString()))
                    .birthdate(new SimpleDateFormat("YYYY-mm-dd").parse(client.get("birthdate").toString()))
                    .build());
        }

        return clients;
    }

    /**
     * Retrieve the ages attribute of the clients
     *
     * @param clients
     * @return
     */
    private List<Integer> getAges(List<ClientModel> clients) {
        return clients.stream()
                .map(ClientModel::getAge)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param client
     */
    private void validateClient(ClientModel client) {
        if (isNull(client) ||
                isNull(client.getName()) ||
                isNull(client.getSurname()) ||
                isNull(client.getAge()) ||
                isNull(client.getBirthdate())) {
            throw new IllegalArgumentException("The client or your attributes does not be null");
        }
    }
}
