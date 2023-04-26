package ru.netolgy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class GameTest {
    Map<String, Players> players = new HashMap<>();
    Game game = new Game();
    Players player1 = new Players(1, "Fedor", 10);
    Players player2 = new Players(2, "Olga", 8);
    Players player3 = new Players(3, "Nick", 12);
    Players player4 = new Players(4, "Travis", 10);


    @Test
    void shouldRegisterNewPlayerAndFindForKeyInMap() {
        players.put("Fedor", player1);
        players.put("Olga", player2);
        players.put("Nick", player3);
        players.put("Travis", player4);

        Players player5 = new Players(5, "Alisa", 11);
        players.put("Alisa", player5);

        Assertions.assertTrue(players.containsValue(player5));
        Assertions.assertEquals(5, players.size());
        Assertions.assertEquals(player5, players.get("Alisa"));
    }

    @Test
    public void roundPlayer1Wins() throws NotRegisteredException {
        game.register(player1);
        game.register(player2);

        Assertions.assertEquals(1, game.gameRound("Fedor", "Olga"));
    }

    @Test
    public void roundPlayer2Wins() throws NotRegisteredException {
        game.register(player2);
        game.register(player3);

        Assertions.assertEquals(2, game.gameRound("Olga", "Nick"));
    }

    @Test
    public void roundDraw() throws NotRegisteredException {
        game.register(player1);
        game.register(player4);

        Assertions.assertEquals(0, game.gameRound("Fedor", "Travis"));
    }

    @Test
    public void roundNotRegisteredPlayer() {
        game.register(player1);

        Assertions.assertThrows(NotRegisteredException.class, () -> game.gameRound("Fedor", "Alisa"));
        Assertions.assertThrows(NotRegisteredException.class, () -> game.gameRound("Alisa", "Fedor"));
    }

    @Test
    public void findNotRegisteredPlayer() {
        game.register(player1);
        Assertions.assertThrows(NotRegisteredException.class, () -> game.findRegisteredPlayer("Vasiliy"));
    }

    @Test
    public void shouldFindRegisteredPlayerForKeys() throws NotRegisteredException {
        game.register(player1);
        game.register(player2);
        game.register(player3);
        game.register(player4);
        Assertions.assertEquals(player1, game.findRegisteredPlayer("Fedor"));
        Assertions.assertEquals(player2, game.findRegisteredPlayer("Olga"));
        Assertions.assertEquals(player3, game.findRegisteredPlayer("Nick"));
        Assertions.assertEquals(player4, game.findRegisteredPlayer("Travis"));
    }
}
