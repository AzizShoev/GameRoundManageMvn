package ru.netolgy;

import java.util.HashMap;
import java.util.Map;

public class Game {
    Map<String, Players> players = new HashMap<>();

    public void register(Players player) {
        players.put(player.name, player);
    }

    Players findRegisteredPlayer(String playerName) throws NotRegisteredException {
        Players player = players.get(playerName);
        if (player == null) {
            throw new NotRegisteredException("Player " + playerName + " not found");
        }
        return player;
    }
    public int gameRound(String name1, String name2) throws NotRegisteredException {
        Players player1 = findRegisteredPlayer(name1);
        Players player2 = findRegisteredPlayer(name2);

        if (player1.strength > player2.strength) {
            return 1;
        } else if (player1.strength < player2.strength) {
            return 2;
        } else {
            return 0;
        }
    }
}