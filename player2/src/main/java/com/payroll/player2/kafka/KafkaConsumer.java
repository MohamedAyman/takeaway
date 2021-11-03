package com.payroll.player2.kafka;

import com.payroll.player2.dto.Player;
import com.payroll.player2.service.PlayerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private PlayerService playerService;

    @KafkaListener(topics = "${kafka.topic.player2}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, Player> record, Acknowledgment acknowledgment) {
        Player player = record.value();
        playerService.playTurn(player.getCurrentNumber());
        acknowledgment.acknowledge();
    }
}
