package com.bookstore.apis;

public class GoogleBooksAPI {
    ApiClient apiClient;

    public GoogleBooksAPI() {
        this.apiClient = new ApiClient("https://www.googleapis.com/books");
    }

    public String getBookByISBN(String ISBN, String key) {
        String parameters = "/v1/volumes?q=isbn:" + ISBN + "&key=" + key;
        return apiClient.fetchDataFromApi(parameters);
    }

}
