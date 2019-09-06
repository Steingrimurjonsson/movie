package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class ResourceMovie {


    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static MovieFacade facade = MovieFacade.getMovieFacade(emf);
    private static Gson gson = new Gson();
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"MOVIES\"}";
    }
   @Path("populate")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        facade.populateMovies();
        return "{\"msg\":\"done!\"}";
    }

    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieById(@PathParam ("id") int id) {
        Movie movie = facade.getMovieByID(id);
        return gson.toJson(movie);
    }
        @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieCount() {
        long count = facade.getMovieCount();
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMovies() {
        List<Movie>  movies = facade.getAllMovies();
        return gson.toJson(movies);
        
    }
       @Path("actorsIn/{name}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAcotorsByMovieName(@PathParam ("name") String name) {
        List<Movie>  actors = facade.getAcotorsByMovieName(name);
        return gson.toJson(actors);
        
    }

    @Path("name/{name}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieByName(@PathParam ("name") String name) {
        List <Movie> movie = facade.getMovieByName(name);
        return gson.toJson(movie);
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Movie entity) {
        throw new UnsupportedOperationException();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Movie entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
}
