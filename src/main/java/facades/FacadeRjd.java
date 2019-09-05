package facades;

import entities.RjdCode;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeRjd {

    private static FacadeRjd instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadeRjd() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeRjd getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeRjd();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long Count = (long)em.createQuery("SELECT COUNT(r) FROM RjdCode r").getSingleResult();
            return Count;
        }finally{  
            em.close();
        }
        
    }

}
