package com.bookstore.apis;

import com.bookstore.item.book.base.BookInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

public class OpenLibraryAPI {
    RestTemplate restTemplate = new RestTemplate();
    String fooResourceUrl
            = "https://openlibrary.org/api/books?bibkeys=";

    String format = "&format=json&jscmd=details";

    @PostMapping
    public BookInfo getRecord(String ISBN) throws JsonProcessingException {
        String result = restTemplate.getForObject(fooResourceUrl + ISBN + format,  String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        //Separate nodes to remove ISBN tree node
        JsonNode originalNode = objectMapper.readTree(result);
        ObjectNode modifiedNode = objectMapper.createObjectNode();

        // detailsNode contains all children nodes of ISBN node
        JsonNode detailsNode = originalNode.get(ISBN);
        if (detailsNode != null && detailsNode.isObject()) {
            detailsNode.fields().forEachRemaining(entry -> {
                modifiedNode.set(entry.getKey(), entry.getValue());
            });
        }

        // Convert the modified node to a JSON string
        String modifiedJsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(modifiedNode);

        return objectMapper.readValue(modifiedJsonString, BookInfo.class);
    }
}
