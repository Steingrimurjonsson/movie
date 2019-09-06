package facades;

import utils.EMF_Creator;
import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasProperty;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class FacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;
    private Movie movie1 = new Movie(1932, "Nøddebo præstekjole", new String[]{"Jepser Nielsen", "Henrik Poulsen", "Freddy Fræk"});
    private Movie movie2 = new Movie(1933, "De døde heste", new String[]{"Ulla Tørnæse", "Pia Køl", "Freddy Fræk"});
    private Movie movie3 = new Movie(1933, "De bløde heste", new String[]{"Ulla Tørnæse", "Pia Køl", "Freddy Fræk"});
    private Movie movie4 = new Movie(1934, "De søde heste", new String[]{"Ulla Tørnæse", "Pia Køl", "Freddy Fræk"});

    public FacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/movie_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = MovieFacade.getMovieFacade(emf);
    }
  @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */


  
    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.persist(movie1);
            em.persist(movie2);
            em.persist(movie3);
            em.persist(movie4);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testMovieCount() {
        assertEquals(4, facade.getMovieCount(), "Expects 4 rows in the database");
    }

    @Test
    public void testGetMovieByID() {
        Movie movie = facade.getMovieByID(movie2.getId());
        assertThat(movie.getActors()[0], containsString("Tørnæse"));
    }
/*
    @Test
    public void testMovieHasActors() {
        Movie movie = facade.getMovieByID(movie1.getId());
        assertThat(movie.getActors(), arrayContaining("Jesper Nielsen"));
    }
*/
}
