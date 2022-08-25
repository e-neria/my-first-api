package Contract.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import DTOS.Show;

@RegisterRestClient
public interface IShowRestClient 
{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/shows/{showId}")
    Show getShowById(@PathParam("showId") Integer showId);
}
