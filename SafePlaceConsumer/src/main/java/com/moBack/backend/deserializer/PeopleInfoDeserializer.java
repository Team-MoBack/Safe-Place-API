package com.moBack.backend.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moBack.backend.entity.PeopleInfo;
import org.apache.kafka.common.serialization.Deserializer;

public class PeopleInfoDeserializer implements Deserializer<PeopleInfo> {
    @Override
    public PeopleInfo deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        PeopleInfo peopleInfo = null;
        try {
            peopleInfo = mapper.readValue(data,PeopleInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return peopleInfo;
    }
}
