package game;

import java.util.*;

public class Board {
    private static final int FINAL_POSITION = 100;
    
    private Map<Player, Integer> playerPositions;
    
    public Board(Set<Player> players) {
        this.playerPositions = new LinkedHashMap<>();
        players.forEach(player -> this.playerPositions.put(player, 0));
    }
    
    public Set<Player> getPlayers() {
        return playerPositions.keySet();    
    }
    
    public int whereIs(Player player) {
        return playerPositions.get(player);
    }
    
    public void move(Player player, int position) {
        playerPositions.put(player, position);
    }
    
    public int getFinalPosition() {
        return FINAL_POSITION;
    }
}
