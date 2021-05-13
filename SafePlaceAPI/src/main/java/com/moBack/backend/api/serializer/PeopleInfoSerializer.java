package com.moBack.backend.api.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moBack.backend.api.entity.PeopleInfo;
import org.apache.kafka.common.serialization.Serializer;

public class PeopleInfoSerializer implements Serializer<PeopleInfo> {

    @Override
    public byte[] serialize(String topic, PeopleInfo data) {
        byte[] serializedBytes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            serializedBytes = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serializedBytes;
    }
}
