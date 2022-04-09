package game;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {
    @Mock
    private Dice mockDice;
    
    @Test
    public void gameOverInSingleSteps() {
        when(mockDice.roll()).thenReturn(1);
        Player viraj = new Player("Viraj");
        Board board = new Board(ImmutableSet.of(viraj));
        Game game = new Game(board, mockDice);
        
        try {
            while(true)
                game.playNext();
        }
        catch (GameOverException gameOverEx) {
            System.out.println(game.getWinner().get());
            assertThat(game.getWinner().get(), equalTo(viraj));
        }
    }

    @Test
    public void firstPlayerWinsInSingleSteps() {
        when(mockDice.roll()).thenReturn(1);
        Player viraj = new Player("Viraj");
        Player sachin = new Player("Sachin");
        Player rahul = new Player("Rahul");
        
        Board board = new Board(ImmutableSet.of(viraj, sachin, rahul));
        Game game = new Game(board, mockDice);

        try {
            while(true)
                game.playNext();
        }
        catch (GameOverException gameOverEx) {
            assertThat(game.getWinner().get(), equalTo(viraj));
        }

        board = new Board(ImmutableSet.of(sachin, viraj, rahul));
        game = new Game(board, mockDice);

        try {
            while(true)
                game.playNext();
        }
        catch (GameOverException gameOverEx) {
            assertThat(game.getWinner().get(), equalTo(sachin));
        }

        board = new Board(ImmutableSet.of(rahul, viraj, sachin));
        game = new Game(board, mockDice);

        try {
            while(true)
                game.playNext();
        }
        catch (GameOverException gameOverEx) {
            assertThat(game.getWinner().get(), equalTo(rahul));
        }
    }

    @Test
    public void fasterPlayerWins() {
        Player viraj = new Player("Viraj");
        Player sachin = new Player("Sachin");

        Board board = new Board(ImmutableSet.of(viraj, sachin));
        Game game = new Game(board, mockDice);

        try {
            while(true) {
                if(game.whoIsNext().equals(viraj))
                    when(mockDice.roll()).thenReturn(ThreadLocalRandom.current().nextInt(1, 10));
                else
                    when(mockDice.roll()).thenReturn(ThreadLocalRandom.current().nextInt(1, 3));
                
                game.playNext();
            }
        }
        catch (GameOverException gameOverEx) {
            assertThat(game.getWinner().get(), equalTo(viraj));
        }
    }
    
    @Test
    public void playerCantGoBeyondFinish() {
        Player viraj = new Player("Viraj");
        Board boardSpy = Mockito.spy(new Board(ImmutableSet.of(viraj)));
        Game game = new Game(boardSpy, mockDice);

        when(boardSpy.getFinalPosition()).thenReturn(4);
        when(mockDice.roll()).thenReturn(5);
        
        game.playNext();
        
        assertThat(boardSpy.whereIs(viraj), equalTo(0));
    }

    @Test
    public void playerMovesByPositionsIndicatedByDiceRoll() {
        Player viraj = new Player("Viraj");
        Board board = new Board(ImmutableSet.of(viraj));
        Game game = new Game(board, mockDice);
        when(mockDice.roll()).thenReturn(5);

        game.playNext();

        assertThat(board.whereIs(viraj), equalTo(5));
    }
}
