
package interfaces;

import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import java.util.List;


public interface Keeping {
    public void saveBooks(List<Book> books);
    public List<Book> loadBooks();
    public void saveAuthors(List<Author> authors);
    public List<Author> loadAuthors();
    public void saveReaders(List<Reader> readers);
    public List<Reader> loadReaders();
    public void saveHistories(List<History> histories);
    public List<History> loadHistories();
}