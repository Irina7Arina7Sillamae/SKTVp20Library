
package myclasses;

import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import facade.AuthorFacade;
import facade.BookFacade;
import facade.HistoryFacade;
import facade.ReaderFacade;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;



public class App {
    private Scanner scanner = new Scanner(System.in);
    
    //---------- Данные библиотеки ----------
    private ReaderFacade readerFacade;
    private BookFacade bookFacade;
    private HistoryFacade historyFacade;
    private AuthorFacade authorFacade;
    //заменили на фасады
    //private List<Book> books = new ArrayList<>();
    //private List<Reader> readers = new ArrayList<>();
    //private List<History> histories = new ArrayList<>();
    //private List<Author> authors = new ArrayList<>();
    // -------- сохранение --------
    //private Keeping keeping = new KeeperToFile();
    //private Keeping keeping = new KeeperToBase();
    
    public App(){
        init();
        //books = keeping.loadBooks();
        //authors = keeping.loadAuthors();
        //readers = keeping.loadReaders();
        //histories = keeping.loadHistories();
    }
    private void init(){
        readerFacade = new ReaderFacade(Reader.class);
        bookFacade = new BookFacade(Book.class);
        historyFacade = new HistoryFacade(History.class);
        authorFacade = new AuthorFacade(Author.class);
    }
    
    public void run(){
       String repeat = "r";
        do{
            System.out.println("----------------------");
            System.out.println("*** Выберите номер задачи:   ");
            System.out.println(" 0: Закончить программу");
            System.out.println(" 1: Добавить книгу");
            System.out.println(" 2: Список книг");
            System.out.println(" 3: Добавить читателя");
            System.out.println(" 4: Список читателей");
            System.out.println(" 5: Выдать книгу читателю");
            System.out.println(" 6: Вернуть книгу");
            System.out.println(" 7: Список выданных книг");
            System.out.println(" 8: Добавить автора");
            System.out.println(" 9: Список авторов");
            System.out.println("10: Изменить данные книги");
            System.out.println("----------------------");
            int task = scanner.nextInt(); scanner.nextLine();
            switch (task) {
                case 0:
                    repeat="q";
                    System.out.println("Пока!");
                    break;
                case 1:
                    addBook();
                    break;
                case 2:
                    printListBooks();
                    break;
                case 3:
                    addReader();
                    break;
                case 4:
                    printListReaders();
                    break;
                case 5:
                    addHistory();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    printListGivenBooks();
                    break;
                case 8:
                    addAuthor();
                    break;
                case 9:
                    printListAuthors();
                    break;
                    case 10:
                    changeBook();
                    break;
                    
                default:
                    System.out.println("*** Выберите цифру из списка!   ");;
            }
        }while("r".equals(repeat));
    }
    private void addBook(){
        System.out.println("--------------------");
        System.out.println("*** Добавление книги ***");
        if(isQuit()){
            return;
        }
        Book book = new Book();
        Set<Integer> setNumbersAuthors = printListAuthors();
        if(setNumbersAuthors.isEmpty()){
            return;
        }
        System.out.println("--------------------");
        System.out.println("*** Если есть авторы книги в списке выберите 1, если нет, выберите 2   ");
        if(getNumber() != 1){
            System.out.println("*** Добавьте автора!  ");
            return;
        }
        System.out.print("*** Сколько авторов у книги:   ");
        int countAuthors = getNumber();
        List<Author> authorsBook = new ArrayList<>();
        for (int i = 0; i < countAuthors; i++) {
            System.out.println("*** Введите номер автора   "+(i+1));
            int numberAuthor = insertNumber(setNumbersAuthors);
            authorsBook.add(authorFacade.find((long)numberAuthor));
        }
        book.setAuthors(authorsBook);
        System.out.print("*** Введите название книги:   ");
        book.setBookName(scanner.nextLine());
        System.out.print("*** Введите год издания книги:   ");
        book.setPublishedYear(getNumber());
        System.out.print("*** Введите количество экземпляров книги:   ");
        book.setQuantity(getNumber());
        book.setCount(book.getQuantity());
        
        //books.add(book);
        //keeping.saveBooks(books);
        bookFacade.create(book);
    }
    private void addReader(){
        if(isQuit()){
            return;
        }
        System.out.println("--------------------");
        System.out.println("*** Добавление читателя ***");
        Reader reader = new Reader();
        System.out.println("*** Имя читателя:   ");
        reader.setFirstname(scanner.nextLine());
        System.out.println("*** Фамилия читателя:   ");
        reader.setLastname(scanner.nextLine());
        System.out.println("*** Телефон читателя:   ");
        reader.setPhone(scanner.nextLine());
        
        readerFacade.create(reader);
        //readers.add(reader);
        //keeping.saveReaders(readers);
        
    }

    private void addHistory() {
        if (isQuit()) {
            return;
        }
        System.out.println("--------------------");
        System.out.println("--- Выдача книги ---");
        History history = new History();
        System.out.println("*** Выберите номер книги:   ");
        Set<Integer> setNumbersBooks = printListBooks();
        if(setNumbersBooks.isEmpty()){
            return;
        }
        int numberBook = insertNumber(setNumbersBooks);
        
        System.out.println("--------------------");
        Set<Integer> setNumbersReaders = printListReaders();
        if(setNumbersReaders.isEmpty()){
            return;
        }
        System.out.println("*** Выберите номер читателя:   ");
        //защита от неправильного ввода
        int numberReader = insertNumber(setNumbersReaders);  
        
        Book book = bookFacade.find((long)numberBook);
        history.setBook(book);
        //history.setBook(books.get(numberBook-1));
        
        history.setReader(readerFacade.find((long)numberReader));
        //history.setReader(readers.get(numberReader-1));
        
        Calendar c = new GregorianCalendar();
        history.setGivenDate(c.getTime());
        
        book.setCount(book.getCount() - 1);
        
        bookFacade.edit(book);
        historyFacade.create(history);
        //keeping.saveBooks(books);
        //histories.add(history);
        //keeping.saveHistories(histories);
        
        System.out.println("--------------------");
        System.out.println("*** Книга "+history.getBook().getBookName()
                            +" выдана читателю "+history.getReader().getFirstname()
                            +" " +history.getReader().getLastname()
        );
        
    }

    private Set<Integer> printListBooks() {
        System.out.println("--------------------");
        System.out.println("*** Список книг ***");
        Set<Integer> setNumbersBooks = new HashSet<>();
        List<Book> books = bookFacade.findAll();
        if(books.isEmpty()){
            System.out.println("*** Список книг пуст, добавьте книги.");
            return new HashSet<>();
        }
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i) != null && books.get(i).getCount() > 0){
                System.out.printf("%1d. %-20s %20s   %-7d В наличии екземпляров:%3dtk%n"
                        ,books.get(i).getId()
                        ,books.get(i).getBookName()
                        ,Arrays.toString(books.get(i).getAuthors().toArray())
                        ,books.get(i).getPublishedYear()
                        ,books.get(i).getCount()
                );
                setNumbersBooks.add(books.get(i).getId().intValue());
             }else if(books.get(i) != null && books.get(i).getQuantity()>0){
                System.out.printf("%1$d. (Книга читается до: %5$s) %2$s. %4$d. %3$s%n"
                        ,books.get(i).getId()
                        ,books.get(i).getBookName()
                        ,Arrays.toString(books.get(i).getAuthors().toArray())
                        ,books.get(i).getPublishedYear()
                        ,getReturnDate(books.get(i))
                );
            }
        }
        return setNumbersBooks;
    }
    
    private Set<Integer> printListAllBooks() {
        System.out.println("--- Список книг ---");
        Set<Integer> setNumbersBooks = new HashSet<>();
        List<Book> books = bookFacade.findAll();
        if(books.isEmpty()){
            System.out.println("*** Список книг пуст, добавьте книги.");
            return new HashSet<>();
        }
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i) != null && books.get(i).getCount() >= 0
                    ){
                System.out.printf("%4d. %11s %11s %d. В наличии екземпляров: %4d%n"
                        ,books.get(i).getId()
                        ,books.get(i).getBookName()
                        ,Arrays.toString(books.get(i).getAuthors().toArray())
                        ,books.get(i).getPublishedYear()
                        ,books.get(i).getCount()
                );
                setNumbersBooks.add(i+1);
            }else if(books.get(i) != null){
                System.out.printf("%4$d. (Книга читается до: %10$s) %10$s. %7$d. %$s%n"
                        ,books.get(i).getId()
                        ,books.get(i).getBookName()
                        ,Arrays.toString(books.get(i).getAuthors().toArray())
                        ,books.get(i).getPublishedYear()
                        ,getReturnDate(books.get(i))
                );
            }
        }
        System.out.println("-------------------");
        return setNumbersBooks;
    }
    private String getReturnDate(Book book){
        History history = historyFacade.find(book);
        if (history == null) return "";
        //for (int i = 0; i < histories.size(); i++) {
            //if(book.getBookName().equals(histories.get(i).getBook().getBookName())
                    //&& histories.get(i).getReturnedDate() == null){
                Date givenDate = history.getGivenDate();
                LocalDate localGivenDate = givenDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                localGivenDate = localGivenDate.plusDays(14);
                return localGivenDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
     
    private Set<Integer> printListReaders() {
        System.out.println("--------------------");
        System.out.println("*** Список читателей ***");
        Set<Integer> setNumbersReaders = new HashSet<>();
        
        List<Reader> readers =readerFacade.findAll();
        if(readers.isEmpty()){
            System.out.println("*** Список читателей пуст.");
            return new HashSet<>();
        }
        for (int i = 0; i < readers.size(); i++) {
            if(readers.get(i) != null){
                System.out.printf("%1d. %11s %-11s Телефон: %10s%n"
                        ,readers.get(i).getId()
                        ,readers.get(i).getFirstname()
                        ,readers.get(i).getLastname()
                        ,readers.get(i).getPhone()
                );
               setNumbersReaders.add(readers.get(i).getId().intValue());
            }
        }
        return setNumbersReaders;
    }

    private void returnBook() {
        System.out.println("--------------------");
        System.out.println("*** Возврат книги ***");
        System.out.println("--- Возврат книги ---");
        if(isQuit()){
            return;
        }
        Set<Integer> setNumbersGivenBooks = printListGivenBooks();
        if(setNumbersGivenBooks.isEmpty()){
            return;
        }
        System.out.print("*** Выберите номер возврщаемой книги:   ");
        int numberHistory = insertNumber(setNumbersGivenBooks);
        Calendar c = new GregorianCalendar();
        History history = historyFacade.find((long)numberHistory);
        history.setReturnedDate(c.getTime());
        //histories.get(numberHistory - 1).setReturnedDate(c.getTime());
        
        Book book = bookFacade.find(history.getBook().getId());
        book.setCount(book.getCount()+ 1);
        bookFacade.edit(book);
        historyFacade.edit(history);

        //for (int i = 0; i < books.size(); i++) {
        //    if(histories.get(numberHistory - 1).getBook().getBookName().equals(books.get(i).getBookName())){
        //        books.get(i).setCount(books.get(i).getCount()+1);
        //        break;
        //    }
        //}
        //keeping.saveBooks(books);
        //keeping.saveHistories(histories);
        System.out.println("--------------------");
        System.out.println("*** Книга "
                +history.getBook().getBookName()
                +" возвращена в библиотеку"
        );
    }

    private Set<Integer> printListGivenBooks() {
        System.out.println("--------------------");
        System.out.println("*** Список читаемых книг: ***");
        Set<Integer> setNumbersBook = new HashSet<>();
        List<History> historiesGivenBooks = historyFacade.findGivenBooks();
        for (int i = 0; i < historiesGivenBooks.size(); i++) {
                System.out.printf("%1d. Книгу:  %-20s  читает:  %-4s  %-11s%n"    
                        ,historiesGivenBooks.get(i).getId()
                        ,historiesGivenBooks.get(i).getBook().getBookName()
                        ,historiesGivenBooks.get(i).getReader().getFirstname()
                        ,historiesGivenBooks.get(i).getReader().getLastname()
                );
                setNumbersBook.add(historiesGivenBooks.get(i).getId().intValue());
            }
        
        if(setNumbersBook.isEmpty()){
            System.out.println("*** Нет читаемых книг!");
            System.out.println("--------------------");
        }
        return setNumbersBook;
    }

    private int getNumber() {//проверка на ввод числа
        int number;
        do{
            String strNumber = scanner.nextLine();
            try {
                number = Integer.parseInt(strNumber);
                return number;
            } catch (NumberFormatException e) {
                System.out.println("*** Попробуй еще раз: ");
            }
        }while(true);
    }

    private int insertNumber(Set<Integer> setNumbers) { //проверка есть ли этот номер в списке
        int number;
        do{
            number = getNumber();
            if(setNumbers.contains(number)){
                return number;
            }
            System.out.println("*** Попробуй еще: ");
        }while(true);
    }

    private Set<Integer> printListAuthors() {
        Set<Integer> numbersAuthors = new HashSet<>();
        List<Author> authors = authorFacade.findAll();
        if(authors.isEmpty()){
            System.out.println("*** Список авторов пуст, добавьте авторов книги.");
            return new HashSet<>();
        }else{
            System.out.println("*** Список авторов:");
        }
        for (int i = 0; i < authors.size(); i++) {
            System.out.printf("%d. %s %s%n"
                    ,authors.get(i).getId()
                    ,authors.get(i).getFirstname()
                    ,authors.get(i).getLastname()
            ); 
            numbersAuthors.add(authors.get(i).getId().intValue());
        }
        return numbersAuthors;
    }

    private void addAuthor() {
        System.out.println("--------------------");
        System.out.println("*** Добавить автора ***");
        if(isQuit()){
            return;
        }
        Author author = new Author();
        System.out.println("*** Имя автора:   ");
        author.setFirstname(scanner.nextLine());
        System.out.println("*** Фамилия автора:   ");
        author.setLastname(scanner.nextLine());
        
        authorFacade.create(author);
        //authors.add(author);
        //keeping.saveAuthors(authors);
    }

     private void changeBook() {
        System.out.println("----- Изменение данных книги ------");
        if(isQuit()){
            return;
        }
        /**
         * 1. Выводим список книг
         * 2. Выбрать номер книги
         * 3. Показать содержимое поля книги
         * 4. Спросить у пользователя, нужно ли менять поле.
         * 5. Ввести новое значение поля.
         * ----- повторить  3-5 для других полей книги
         * сохранить список существующих книг 
         */
         Set<Integer> changeNumbers = new HashSet<>();
        changeNumbers.add(1);
        changeNumbers.add(2);
        Set<Integer> setNumbersBooks = printListAllBooks();
        if(setNumbersBooks.isEmpty()){
            return;
        }
        System.out.println("Введите номер книги: ");
        int numberBook = insertNumber(setNumbersBooks);
        Book book = bookFacade.find((long)numberBook);
        System.out.println("Название книги: " + book.getBookName());
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        int change = insertNumber(changeNumbers);
        if(1 == change){
            System.out.println("Введите новое название книги: ");
            book.setBookName(scanner.nextLine());
        }
        System.out.println("Год издания книги: " + book.getPublishedYear());
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        change = insertNumber(changeNumbers);
        if(1 == change){
            System.out.println("Введите новый год издания книги: ");
            book.setPublishedYear(getNumber());
        }
        System.out.println("Количество экземпляров книги: " + book.getQuantity());
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        change = insertNumber(changeNumbers);
        if(1 == change){
            System.out.println("Введите новое количество экземпляров книги: ");
            int oldQuantity = book.getQuantity();
            int oldCount = book.getCount();
            int newQuantity;
            do{
                newQuantity = getNumber();
                //if(newQuantity >= oldQuantity - oldCount){
                if(newQuantity >= oldQuantity - oldCount){
                    break;
                }
                System.out.println("Книг должно быть больше.");
            }while(true);
            int newCount = oldCount + (newQuantity - oldQuantity);
            
            book.setQuantity(newQuantity);
            book.setCount(newCount);
        }
        System.out.println("Авторы книги: ");
        for (int i = 0; i < book.getAuthors().size(); i++) {
            System.out.printf("%d. %s %s%n"
                    ,i+1
                    ,book.getAuthors().get(i).getFirstname()
                    ,book.getAuthors().get(i).getLastname()
            );
        }
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        change = insertNumber(changeNumbers);
        if(1 == change){
            book.getAuthors().clear();
            Set<Integer> setNumbersAuthors = printListAuthors();
            if(setNumbersAuthors.isEmpty()){
                return;
            }
            System.out.println("Введите новый новое количество авторов: ");
            int countAuthors = getNumber();
            for (int i = 0; i < countAuthors; i++) {
                System.out.println("Введите автора "+(i+1)+": ");
                int numberAuthor = insertNumber(setNumbersAuthors);
                book.getAuthors().add(authorFacade.find((long)numberAuthor));
            }
        }
        bookFacade.edit(book);
    }
    private boolean isQuit(){
        System.out.println("Чтобы закончить задачу нажми \"q\"");
        String q = scanner.nextLine();
        if("q".equals(q)){
            return true;
        }else{
            return false;
        }
    }
   
}