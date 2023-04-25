package ru.netolgy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GameTest {
    Players player1 = new Players(15, "Ivan", 350);
    Players player2 = new Players(45, "Charly", 270);
    Players player3 = new Players(22, "Volodka", 200);
    Players player4 = new Players(45, "Misterio", 200);

    @Test
    public void shouldFindNotRegisteredPlayers() {
        Game game = new Game();

        game.register(player1);
        game.register(player2);

        Assertions.assertThrows(NotRegisteredException.class, () -> game.gameRound("David", "Ivan"));
        Assertions.assertThrows(NotRegisteredException.class, () -> game.gameRound("Ivan", "David"));
    }

    @Test
    public void shouldRegisterAndFindAllPlayer() {
        Game game = new Game();

        game.register(player1);
        game.register(player3);
        game.register(player2);
        game.register(player4);

        Assertions.assertEquals(4, game.players.size());

        List<Players> expected = List.of(player1, player3, player2, player4);
        Assertions.assertEquals(expected, game.players);
    }

    @Test
    public void shouldShowResultPlayer1Wins() throws NotRegisteredException {
        Game game = new Game();

        game.register(player1);
        game.register(player2);

        Assertions.assertEquals(1, game.gameRound("Ivan", "Charly"));
    }

    @Test
    public void shouldShowResultPlayer2Wins() throws NotRegisteredException {
        Game game = new Game();

        game.register(player1);
        game.register(player2);

        Assertions.assertEquals(2, game.gameRound("Charly", "Ivan"));
    }

    @Test
    public void shouldResultGameRoundDraw() throws NotRegisteredException {
        Game game = new Game();

        game.register(player3);
        game.register(player4);

        Assertions.assertEquals(0, game.gameRound("Volodka", "Misterio"));
    }
}
