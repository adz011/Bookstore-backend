package com.bookstore.item.book.category;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CategoryListDeserializer extends JsonDeserializer<Set<Category>> {
    @Override
    public Set<Category> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode node = mapper.readTree(jsonParser);

        Set<Category> categoryEntities = new HashSet<>();
        for (JsonNode categoryNode : node) {
            Category category = new Category();
            category.setCategory(categoryNode.asText());
            categoryEntities.add(category);
        }


        return categoryEntities;
    }
}
