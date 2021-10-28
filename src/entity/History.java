
package entity;

import java.io.Serializable;
import java.util.Date;


public class History implements Serializable{
    private Reader reader;
    private Book book;
    private Date givenDate;
    private Date returnedDate;

    public History() {
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(Date givenDate) {
        this.givenDate = givenDate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    @Override
    public String toString() {
        return "History / " 
                + "читатель: " + reader 
                + " / книга=" + book 
                + " / дата выдачи=" + givenDate 
                + " / дата возврата=" + returnedDate ;
    }
    
}