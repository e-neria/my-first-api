package Services;

import Contract.Service.HelloWorldServiceInterface;
import Response.HandlerResponse;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class HelloWorldService implements HelloWorldServiceInterface
{
    public HandlerResponse helloWorld(String language)
    {
        String message = "success";
        Integer httpCode = 200;
        language = (language == null) ? "" : language;

        HandlerResponse.MetadataResponse metadataResponse = new HandlerResponse.MetadataResponse();
        HandlerResponse.DataResponse dataResponse = new HandlerResponse.DataResponse();

        ArrayList<String> data = new ArrayList<>();
        data.add("Php");
        data.add("Java");
        data.add("Python");
        data.add("JQuery");

        if(language != "" && !data.contains(language)){
            message = "error";
            httpCode = 404;
            data = new ArrayList<>();
        }

        metadataResponse.setMessage(message);
        metadataResponse.setHttpCode(httpCode);
        dataResponse.setData(data);

        HandlerResponse handlerResponse = new HandlerResponse(metadataResponse, dataResponse);

        return handlerResponse;
    }
}
