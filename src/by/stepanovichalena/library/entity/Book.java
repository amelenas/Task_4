package by.stepanovichalena.library.entity;

import java.io.Serializable;

public class Book implements Serializable, Comparable {
    private static final long serialVersionUID = 354654523246754623L;
    private int id;
    private String title;
    private String authorsName;

    public Book() {
    }

    public Book(int id, String title, String authorsName) {
        this.id = id;
        this.title = title;
        this.authorsName = authorsName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorsName() {
        return authorsName;
    }

    public void setAuthorsName(String authorsName) {
        this.authorsName = authorsName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        Book book = (Book) o;
        return getId() == book.getId() && getAuthorsName().equals(book.getAuthorsName()) && getTitle().equals(book.getTitle());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getId();
        result = prime * result + getAuthorsName().hashCode();
        result = prime * result + getTitle().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder(this.getClass() + "{" +
                "id=" + id +
                ", authorsName='" + authorsName + '\'' +
                ", title='" + title + '\'' +
                '}').toString();
    }

    @Override
    public int compareTo(Object o) {
        Book book = (Book) o;
        return id-book.id;
    }
}
