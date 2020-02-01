package com.intercorp.assessment.entities.builders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercorp.assessment.entities.aws.ForDynamoDB;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

public class DynamoBuilder {

    public static <T> Map saveObject(T object, ForDynamoDB forDynamoDB) {
        HashMap<String, T> body = bodyParameters(forDynamoDB);
        body.put("object", object);
        return body;
    }

    public static String getAllObject(ForDynamoDB forDynamoDB, String urlbase) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(urlbase)
                .queryParams(buildMultiValueMap(forDynamoDB));
        return uriComponentsBuilder.toUriString();
    }

    private static MultiValueMap buildMultiValueMap(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        MultiValueMap parameters = new LinkedMultiValueMap<String, String>();
        Map<String, String> maps = objectMapper.convertValue(object, new TypeReference<Map<String, String>>() {
        });
        parameters.setAll(maps);
        return parameters;
    }

    private static HashMap bodyParameters(ForDynamoDB forDynamoDB) {
        HashMap<String, ForDynamoDB> dynamoItem = new HashMap<>();
        dynamoItem.put("dynamo", forDynamoDB);
        return dynamoItem;
    }
}
