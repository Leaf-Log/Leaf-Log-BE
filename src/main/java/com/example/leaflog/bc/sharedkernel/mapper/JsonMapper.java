package com.example.leaflog.bc.sharedkernel.mapper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonMapper {

    private final ObjectMapper objectMapper;

    public String toJson(Object event){
        try{
            return objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e){
            throw new RuntimeException(e); //TODO: 나중에 추가할거지롱
        }
    }

    public <T> T fromJson(String eventJson, Class<T> type){
        try{
            return objectMapper.readValue(eventJson, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e); //TODO: 나중에 추가할거지롱
        }
    }
}
