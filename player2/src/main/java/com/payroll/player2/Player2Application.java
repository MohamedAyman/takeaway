package com.payroll.player2;

import com.payroll.player2.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Player2Application implements CommandLineRunner {

    @Autowired
    PlayerService playerService;

    @Value("${first.move}")
    private boolean firstMove;

    @Value("${automatic.player}")
    private boolean automaticPlayer;

    public static void main(String[] args) {
        SpringApplication.run(Player2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (firstMove && automaticPlayer) {
            playerService.firstMove();
        }
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
