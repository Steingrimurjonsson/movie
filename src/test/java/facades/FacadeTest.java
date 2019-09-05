package facades;

import utils.EMF_Creator;
import entities.Movie;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class FacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;
    private String[] actor1 = {"a", "b", "c"};
    private String[] actor2 = {"a", "b", "c"};
    private String[] actor3 = {"a", "b", "c"};
    private String[] actor4 = {"a", "b", "c"};
    private Movie movie1 = new Movie(2001, "movie1", actor1);
    private Movie movie2 = new Movie(2002, "movie2", actor2);
    private Movie movie3 = new Movie(2003, "movie3", actor3);
    private Movie movie4 = new Movie(2004, "movie4", actor4);

    public FacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/movie_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = MovieFacade.getMovieFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = MovieFacade.getMovieFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
  /*  @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllMovies").executeUpdate();
            em.persist(movie1);
            em.persist(movie2);
            em.persist(movie3);
            em.persist(movie4);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
*/
    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }
/*
    @Test
    public void testgetAllMovies() {
        Movie movie = new Movie();
        ArrayList<Movie> movies = new ArrayList();
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        assertEquals(movies, facade.getAllMovies());
    }
*/
    

}
