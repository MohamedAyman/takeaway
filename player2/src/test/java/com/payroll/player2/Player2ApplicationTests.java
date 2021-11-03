package com.payroll.player2;

import com.payroll.player2.kafka.KafkaConsumer;
import com.payroll.player2.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class Player2ApplicationTests {

    @Autowired
    private PlayerService playerService;

    @Test
    void testNextMoveOne() {
        assertEquals(1, playerService.nextMove(5));
    }

    @Test
    void testNextMoveZero() {
        assertEquals(0, playerService.nextMove(6));
    }

    @Test
    void testNextMoveNegativeOne() {
        assertEquals(-1, playerService.nextMove(7));
    }

    @Test
    void testCheckWinNumberSuccess() {
        assertEquals(true, playerService.checkWinNumber(1));
    }

    @Test
    void testCheckWinNumberFail() {
        assertEquals(false, playerService.checkWinNumber(6));
    }
}
