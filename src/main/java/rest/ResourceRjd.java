package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.RjdCode;
import utils.EMF_Creator;
import facades.FacadeRjd;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("rjd")
public class ResourceRjd {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/rjd",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final FacadeRjd FACADE =  FacadeRjd.getFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getCount() {
        long count = FACADE.getCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(RjdCode entity) {
        throw new UnsupportedOperationException();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(RjdCode entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
}
