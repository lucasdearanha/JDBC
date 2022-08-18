package models;

import java.util.Date;
import java.util.Objects;

public class BookLoan {
    private Long id;
    private Date dateLoan;
    private Date returnLoan;

    private User user;
    private Book book;

    public BookLoan() {
    }

    public BookLoan(Date dateLoan, Date returnLoan, User user, Book book) {
        this.dateLoan = dateLoan;
        this.returnLoan = returnLoan;
        this.user = user;
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateLoan() {
        return dateLoan;
    }

    public void setDateLoan(Date dateLoan) {
        this.dateLoan = dateLoan;
    }

    public Date getReturnLoan() {
        return returnLoan;
    }

    public void setReturnLoan(Date returnLoan) {
        this.returnLoan = returnLoan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookLoan bookLoan = (BookLoan) o;
        return id.equals(bookLoan.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BookLoan{" +
                "id=" + id +
                ", dateLoan=" + dateLoan +
                ", returnLoan=" + returnLoan +
                ", user=" + user +
                ", book=" + book +
                '}';
    }
}
