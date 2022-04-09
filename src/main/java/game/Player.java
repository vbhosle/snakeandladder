package game;

import java.util.Objects;
import java.util.UUID;

public class Player {
    private String name;
    private UUID id;
    
    public Player(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return id.equals(player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
