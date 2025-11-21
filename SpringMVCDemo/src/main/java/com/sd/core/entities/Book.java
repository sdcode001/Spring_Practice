package com.sd.core.entities;

import java.util.Collections;
import java.util.List;

public final class Book {
    private final int id;
    private final String title;
    private final List<String> authors;
    private final double amazonRating;
    private final String imageUrl;

    public Book(int id, String title, List<String> authors, double amazonRating, String imageUrl) {
        this.id = id;
        this.title = title;
        this.authors = Collections.unmodifiableList(authors);
        this.amazonRating = amazonRating;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public double getAmazonRating() {
        return amazonRating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", amazonRating=" + amazonRating +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}