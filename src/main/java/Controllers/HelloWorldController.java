package Controllers;

import Contract.Service.HelloWorldServiceInterface;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import Response.HandlerResponse;

@Path("api")
public class HelloWorldController
{
    @Inject
    HelloWorldServiceInterface helloWorldService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/helloWorld")
    public Response getHelloWorld(@QueryParam("language") String language)
    {
        HandlerResponse handlerResponse = helloWorldService.helloWorld(language);
        return Response.status(handlerResponse.getMetadata().getHttpCode()).entity(handlerResponse).build();
    }
}
