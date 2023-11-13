package com.bookstore.item.book.author;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
@Setter
public class AuthorListDeserializer extends JsonDeserializer<Set<Author>> {


    @Override
    public Set<Author> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode node = mapper.readTree(jsonParser);

        Set<Author> authorEntities = new HashSet<>();
        for (JsonNode authorNode : node) {
            Author author = new Author();
            author.setAuthor(authorNode.asText());
            authorEntities.add(author);
        }


        return authorEntities;
    }
}