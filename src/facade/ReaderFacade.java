
package facade;


import entity.Reader;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class ReaderFacade extends AbstractFacade<Reader> {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SKTVp20LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    public ReaderFacade(Class<Reader> entityClass) {
        super(entityClass);
    }
 
    @Override
    protected EntityManager getEntityManager() {
       return em;
       
    }
}