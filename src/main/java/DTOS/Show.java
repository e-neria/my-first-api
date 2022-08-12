package DTOS;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Show 
{
    Integer id;
    String url;
    String name;
    String language;
    List<?> genres;
    Network network;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Network
    {
        Integer id;
        String name;
        Country country;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Country
    {
        String name;
        String code;
        String timezone;
    }
}
