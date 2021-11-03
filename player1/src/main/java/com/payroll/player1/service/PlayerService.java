package com.payroll.player1.service;

import com.payroll.player1.dto.Player;
import com.payroll.player1.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

import static com.payroll.player1.utils.Utils.print;

@Service
public class PlayerService {
    @Value("${player.domain.endpoint.receiver}")
    private String playerDomain;

    @Value("${player.endpoint.receiver}")
    private String playerEndpointReceiver;

    private int gameMove[] = {-1, 0, 1};

    private final int THREE = 3;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private RestTemplate restTemplate;

    public void firstMove() {
        Random random = new Random();
        int n = Math.abs(random.nextInt());
        String firstMoveNumber = String.format("The first move number is %d", n);
        print(firstMoveNumber);
        sendNewNumber(n);
    }

    public void playTurn(int number) {
        int nextMove = nextMove(number);
        String printNextMove = String.format("Current number %d, and will add to it %d", number, nextMove);
        print(printNextMove);
        int newNumber = (number + nextMove) / THREE;
        if (!checkWinNumber(newNumber)) {
            sendNewNumber(newNumber);
        }
    }

    public int nextMove(int number) {
        for (int j : gameMove) {
            if ((number + j) % THREE == 0) {
                return j;
            }
        }
        return 0;
    }

    public boolean checkWinNumber(int number) {
        if (number == 1) {
            String winner = "You are the winner!!";
            print(winner);
            return true;
        }
        return false;
    }

    public void sendNewNumber(int number) {
        String printNewNumber = String.format("Send number %d to next player", number);
        print(printNewNumber);
        Player p = new Player(number);
        if (!sendUsingRestEndPoint(p)) {
            sendUsingMessaging(p);
        }
    }

    public boolean sendUsingRestEndPoint(Player player) {
        try {
            HttpEntity<Player> request = new HttpEntity<>(player);
            restTemplate.postForObject(playerDomain + playerEndpointReceiver, request, Void.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void sendUsingMessaging(Player player) {
        kafkaProducer.sendUsingKafka(player);
    }
}
