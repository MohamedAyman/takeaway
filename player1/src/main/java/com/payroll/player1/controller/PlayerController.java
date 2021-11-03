package com.payroll.player1.controller;

import com.payroll.player1.dto.Player;
import com.payroll.player1.service.PlayerService;
import com.payroll.player1.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.payroll.player1.utils.Utils.print;

@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/player", method = RequestMethod.POST)
    ResponseEntity<String> receivePlayer(@RequestBody Player player) {
        int currentNumber = player.getCurrentNumber();
        String receivedNumber = "Player received number " + currentNumber;
        print(receivedNumber);
        playerService.playTurn(currentNumber);
        return new ResponseEntity<>(player.getCurrentNumber() + "", HttpStatus.OK);
    }

    @RequestMapping(value = "/player/log", method = RequestMethod.GET)
    ResponseEntity<String> playerLog() {
        return new ResponseEntity<>(Utils.getLogs(), HttpStatus.OK);
    }
}
