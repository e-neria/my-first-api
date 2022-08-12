package Controllers;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Contract.Service.IShowService;
import Response.HandlerResponse;

@Path("api/tvmaze")
public class ShowController 
{
    @Inject
    IShowService showService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getShow/{showId}")
    public Response getShowById(@PathParam("showId") Integer showId)
    {
        HandlerResponse handlerResponse = showService.getShowById(showId);
        return Response.status(handlerResponse.getMetadata().getHttpCode()).entity(handlerResponse).build();
    }
}
