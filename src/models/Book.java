package models;

import java.util.Objects;

public class Book {
    private Long id;
    private String title;
    private Integer numPages;
    private Double fineAmount;
    private Publisher publisher;
    private Genre genre;
    private Collection collection;

    public Book() {
    }

    public Book(String title, Integer numPages, Double fineAmount, Publisher publisher,
                Genre genre, Collection collection) {
        this.title = title;
        this.numPages = numPages;
        this.fineAmount = fineAmount;
        this.publisher = publisher;
        this.genre = genre;
        this.collection = collection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumPages() {
        return numPages;
    }

    public void setNumPages(Integer numPages) {
        this.numPages = numPages;
    }

    public Double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(Double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", numPages=" + numPages +
                ", fineAmount=" + fineAmount +
                ", publisher=" + publisher +
                ", genre=" + genre +
                ", collection=" + collection +
                '}';
    }
}
