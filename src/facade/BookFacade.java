
package facade;

import entity.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class BookFacade extends AbstractFacade<Book> {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SKTVp20LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    public BookFacade(Class<Book> entityClass) {
        super(entityClass);
    }
 
    @Override
    protected EntityManager getEntityManager() {
       return em;
       
    }
}
