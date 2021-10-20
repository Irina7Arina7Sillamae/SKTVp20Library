
package entity;
import java.io.Serializable;
import java.util.Arrays;


public class Book implements Serializable{
    private String bookName;
    private Author[] authors;
    private int publishedYear;

    public Book() {
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    @Override
    public String toString() {
        return "Книги: {" + "название книги = " + bookName + ", автор = " + Arrays.toString(authors) + ", год издания = " + publishedYear + '}';
    }
    
    
    }
    
