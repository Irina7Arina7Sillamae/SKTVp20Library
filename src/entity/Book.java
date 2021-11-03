
package entity;
import java.io.Serializable;
import java.util.Arrays;


public class Book implements Serializable{
    
    private String bookName;
    private Author[] authors;
    private int publishedYear;
    private int quantity;
    private int count;
    
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "* Книга * " 
                + " название книги: " + bookName 
                + ", автор: " + Arrays.toString(authors) 
                + ", год изания: " + publishedYear 
                + ", quantity: " + quantity 
                + ", count: " + count ;
    }
    
    
    }
    
