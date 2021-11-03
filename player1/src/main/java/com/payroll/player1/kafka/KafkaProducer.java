package com.payroll.player1.kafka;

import com.payroll.player1.dto.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, Player> kafkaTemplate;

    @Value("${kafka.topic.player2}")
    private String kafkaTopic;

    public void sendUsingKafka(Player player) {
        kafkaTemplate.send(kafkaTopic, player);
    }
}
