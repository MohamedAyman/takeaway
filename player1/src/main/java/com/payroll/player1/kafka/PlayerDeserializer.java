package com.payroll.player1.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payroll.player1.dto.Player;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class PlayerDeserializer implements Deserializer {
    @Override
    public void configure(Map map, boolean b) {
    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        Player model = null;
        try {
            model = mapper.readValue(bytes, Player.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Player) model;
    }

    @Override
    public void close() {
    }
}
