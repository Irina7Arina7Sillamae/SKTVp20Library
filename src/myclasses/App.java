
package myclasses;

import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class App {
    public void run() {
        
        Author author1 = new Author();
        author1.setFirstname("Lev");
        author1.setLastname("Tolstoi");
        Author[] authors = new Author[1];
        authors[0] = author1;
        
        Book book1 = new Book();
        book1.setAuthors(authors);
        book1.setBookName("Voina i mir");
        book1.setPublishedYear(2021);
        
        Reader reader1 = new Reader();
        reader1.setFirstName("Irina");
        reader1.setLastName("Siskova");
        reader1.setPhone("58113989");
        
        History history1 = new History();
        history1.setBook(book1);
        history1.setReader(reader1);
        Calendar c = new GregorianCalendar();
        history1.setGivenDate(c.getTime());
        System.out.println(history1.toString());
        
        history1.setReturnedDate(c.getTime());
        System.out.println(history1.toString());
      
        
        
        
        
        
    }
    
}
