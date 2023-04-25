package ru.netolgy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public List<Players> players= new ArrayList<>();

    public void register(Players player) {
        players.add(player);
    }

    public Players findRegisteredPlayer(String name) {
        for (Players player : players) {
            if (player.name.equals(name)) {
                return player;
            }
        }
        return null;
    }

    public int gameRound(String name1, String name2) throws NotRegisteredException {
        Players player1 = findRegisteredPlayer(name1);
        Players player2 = findRegisteredPlayer(name2);
        if (player1 == null || player2 == null) {
            throw new NotRegisteredException("Player name not found");
        }
        if (player1.strength > player2.strength) {
            return 1;
        } else if (player1.strength < player2.strength) {
            return 2;
        } else {
            return 0;
        }
    }
}
