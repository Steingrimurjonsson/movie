package facades;

import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    
    private MovieFacade() {}
    
    
 
    public static MovieFacade getMovieFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
 
    public long getMovieCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long movieCount = (long)em.createQuery("SELECT COUNT(m) FROM Movie m").getSingleResult();
            return movieCount;
        }finally{  
            em.close();
        }
        
    }
    
    public Movie getMovieByID(int id) {
        EntityManager em = emf.createEntityManager();
        try{
            Movie movie = em.find(Movie.class, id);
            return movie;
        }finally{
            em.close();
        }
    }

    public List<Movie> getMovieByName(String name) {
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery <Movie> query =
                    em.createQuery("Select m from Movie m where m.name =:name", Movie.class);
            return query.setParameter("name", name).getResultList();
        } finally{
            em.close();
        }
    }

    public Movie addMovie(int year, String name, String[] actors) {
        Movie movie = new Movie();
        movie = new Movie(year, name, actors);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
            return movie;
        } finally {
            em.close();
        }
    }

    public List<Movie> getAllMovies() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query
                    = em.createQuery("Select m from Movie m", Movie.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Movie> getAcotorsByMovieName(String name) {
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery <Movie> query =
                    em.createQuery("Select actors from Movie m where m.name =:name", Movie.class);
            return query.setParameter("name", name).getResultList();
        } finally{
            em.close();
        }
    }
    public void populateMovies() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.persist(new Movie(1932, "Nøddebo præstekjole", new String[]{"Jepser Nielsen", "Henrik Poulsen", "Freddy Fræk"}));
            em.persist(new Movie(1933, "De døde heste", new String[]{"Ulla Tørnæse", "Pia Køl", "Freddy Fræk"}));
            em.persist(new Movie(1933, "De bløde heste", new String[]{"Ulla Tørnæse", "Pia Køl", "Freddy Fræk"}));
            em.persist(new Movie(1934, "De søde heste", new String[]{"Ulla Tørnæse", "Pia Køl", "Freddy Fræk"}));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}