package com.bookstore.item.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;
import java.util.Map;


@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    private String title;
    private List<String> authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private List<IndustryIdentifier> industryIdentifiers;
    private Map<String, Boolean> readingModes;
    private int pageCount;
    private String printType;
    private List<String> categories;
    private String maturityRating;
    private boolean allowAnonLogging;
    private String contentVersion;
    private PanelizationSummary panelizationSummary;
    private ImageLinks imageLinks;
    private String language;
    private String previewLink;
    private String infoLink;
    private String canonicalVolumeLink;


    @NoArgsConstructor
    @Getter
    @Setter
    public static class IndustryIdentifier {
        private String type;
        private String identifier;


    }
    @NoArgsConstructor
    @Getter
    @Setter
    public static class PanelizationSummary {
        private boolean containsEpubBubbles;
        private boolean containsImageBubbles;

        // Getter and setter methods for containsEpubBubbles and containsImageBubbles

        // ...
    }
    @NoArgsConstructor
    @Getter
    @Setter
    public static class ImageLinks {
        private String smallThumbnail;
        private String thumbnail;

        // Getter and setter methods for smallThumbnail and thumbnail

        // ...
    }
    public Book() {

    }
}
