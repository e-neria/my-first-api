package Services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import Contract.RestClient.IShowRestClient;
import Contract.Service.IShowService;
import DTOS.Show;
import Response.HandlerResponse;
import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class ShowService implements IShowService
{
    // Case 2: RestClient
    @Inject
    @RestClient
    IShowRestClient showRestClient;

    @Override
    public HandlerResponse getShowById(Integer showId)
    {
        String message = "success";
        Integer httpCode = Response.Status.OK.getStatusCode();

        HandlerResponse.MetadataResponse metadataResponse = new HandlerResponse.MetadataResponse();
        HandlerResponse.DataResponse dataResponse = new HandlerResponse.DataResponse();

        Show show = new Show();

        try {
            show = showRestClient.getShowById(showId);
        } catch (Exception e) {
            System.out.println("ShowService: Ocurrio un error al obtener la información del endPoint de tvmaze. Message: " + e.getMessage());
            show = null;
            message = "fail";
            httpCode = Response.Status.BAD_REQUEST.getStatusCode();
        }

        metadataResponse.setMessage(message);
        metadataResponse.setHttpCode(httpCode);
        dataResponse.setData(show);

        HandlerResponse handlerResponse = new HandlerResponse(metadataResponse, dataResponse);

        return handlerResponse;
    }


    // Case 1: ClientBuilder 
    // @Override
    // public HandlerResponse getShowById(Integer showId) 
    // {
    //     String message = "success";
    //     Integer httpCode = Response.Status.OK.getStatusCode();

    //     HandlerResponse.MetadataResponse metadataResponse = new HandlerResponse.MetadataResponse();
    //     HandlerResponse.DataResponse dataResponse = new HandlerResponse.DataResponse();

    //     Show show = new Show();

    //     try{
    //         ClientBuilder builder = ClientBuilder.newBuilder();
    //         Client client = builder.build();
    //         WebTarget webTarget = client.target("https://api.tvmaze.com/shows/" + showId);
    //         Response response = webTarget.request().get();

    //         if(response.getStatus() == Response.Status.OK.getStatusCode()){
    //             String entityResponse = response.readEntity(String.class);
    //             System.out.println(entityResponse);
    //             JsonObject jsonObject = new JsonObject(entityResponse);
    //             ObjectMapper objectMapper = new ObjectMapper();
    //             objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

    //             show = objectMapper.readValue(jsonObject.encode(), new TypeReference<Show>() {});

    //         }else{
    //             message = "error";
    //             httpCode = Response.Status.NOT_FOUND.getStatusCode();
    //             show = null;
    //         }


    //     }catch(Exception exception){
    //         System.out.println("Ocurrio un error al consumir el endPoint para recuperar el show. Message: " + exception.getMessage());
    //         httpCode = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
    //         message = "fail";
    //         show = null;
    //     }

    //     metadataResponse.setMessage(message);
    //     metadataResponse.setHttpCode(httpCode);
    //     dataResponse.setData(show);

    //     HandlerResponse handlerResponse = new HandlerResponse(metadataResponse, dataResponse);

    //     return handlerResponse;
    // }
    
}
