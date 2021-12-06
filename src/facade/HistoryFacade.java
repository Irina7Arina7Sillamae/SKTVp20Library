
package facade;

import entity.Book;
import entity.History;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class HistoryFacade extends AbstractFacade<History> {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SKTVp20LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    public HistoryFacade(Class<History> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
       return em;
       
    }

    public History find(Book book) {
       try {
           return (History) em.createQuery("SELECT h FROM History h WHERE h.book = :book AND h.returnedDate = null")
                   .setParameter("book", book)
                   .getSingleResult();
       }catch (Exception e){
           
       }
        return null;
    }

    public List<History> findGivenBooks() {
        try {
            return em.createQuery("SELECT h FROM History h WHERE h.returnedDate = null")
                    .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
