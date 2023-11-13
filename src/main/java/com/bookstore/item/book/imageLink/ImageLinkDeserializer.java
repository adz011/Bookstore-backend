package com.bookstore.item.book.imageLink;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageLinkDeserializer extends JsonDeserializer <ImageLinks> {
    @Override
    public ImageLinks deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode node = mapper.readTree(jsonParser);

        String thumbnail = node.get("thumbnail").asText();
        String smallThumbnail = node.get("smallThumbnail").asText();
        ImageLinks imageLinks = new ImageLinks();

        if(thumbnail != null){
            imageLinks.setThumbnail(thumbnail);
        }
        if(smallThumbnail != null){
            imageLinks.setSmallThumbnail(smallThumbnail);
        }

        return imageLinks;
    }

}
