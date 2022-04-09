package game;

import java.util.*;

public class Game {
    private Board board;
    private Dice dice;
    private Player[] players;
    private int nextPlayerIndex;
    private Optional<Player> winner = Optional.empty();
    
    public Game(Board board, Dice dice) {
        this.board = board;
        this.players = new Player[this.board.getPlayers().size()];
        this.dice = dice;

        Iterator<Player> playerIterator = this.board.getPlayers().iterator();
        for (int i = 0; i < this.board.getPlayers().size(); i++) {
            this.players[i] = playerIterator.next();
        }
        
        this.nextPlayerIndex = 0;
    }
    
    public Player whoIsNext() {
        isOver();
        return players[nextPlayerIndex % players.length]; //deciding the next player
    }
    
    public void playNext() {
        isOver();
        Player player = whoIsNext();
        int rolledNumber = dice.roll();
        move(player, rolledNumber);
    }

    private void move(Player player, int rolledNumber) {
        isOver();
        int currentPosition = this.board.whereIs(player);
        int nextPosition = currentPosition + rolledNumber;
        if(nextPosition <= board.getFinalPosition()) //validating the move
            board.move(player, nextPosition);
        
        checkWinner(player); 
    }

    //deciding the winner
    private void checkWinner(Player player) {
        if(board.whereIs(player) == board.getFinalPosition()) {
            this.winner = Optional.of(player);
        }
    }

    public void isOver() {
        if(winner.isPresent())
            throw new GameOverException();
    }
    
    public Optional<Player> getWinner() {
        return winner;
    }
}
