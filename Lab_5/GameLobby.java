import java.util.ArrayList;
import java.util.List;

interface Player {
    void joinGame();
    void leaveGame();
    void sendMessage(String message);
    void receiveMessage(String message);
    String getPlayerType();
    String getName();
}

abstract class AbstractPlayer implements Player {
    protected String name;
    protected GameLobby lobby;

    public AbstractPlayer(String name, GameLobby lobby) {
        this.name = name;
        this.lobby = lobby;
    }
    
    @Override
    public void joinGame() {
        this.lobby.registerPlayer(this);
        System.out.printf("[GameLobby] %s %s has joined the lobby.\n", this.getPlayerType(), this.name);
    }
    
    @Override
    public void leaveGame() {
        System.out.printf("[GameLobby] %s %s has left the lobby.\n", this.getPlayerType(), this.name);
        this.lobby.removePlayer(this);
    }
    
    @Override
    public void sendMessage(String message) {
        System.out.printf("[%s] sends: \"%s\"\n", this.name, message);
        System.out.printf("[GameLobby] Message from %s: \"%s\"\n", this.name, message);
        this.lobby.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.printf("[%s] received: \"%s\"\n", this.name, message);
    }

    public abstract String getPlayerType();

    public String getName() {
        return this.name;
    }
}

class HumanPlayer extends AbstractPlayer {

    public HumanPlayer(String name, GameLobby lobby) {
        super(name, lobby);
    }

    @Override
    public String getPlayerType() {
        return "HumanPlayer";
    }
  }

class AIPlayer extends AbstractPlayer {

    public AIPlayer(String name, GameLobby lobby) {
        super(name, lobby);
    }
    @Override
    public String getPlayerType() {
        return "AIPlayer";
    }
    
}

class Spectator extends AbstractPlayer {

    public Spectator(String name, GameLobby lobby) {
        super(name, lobby);
    }

    @Override
    public String getPlayerType() {
        return "Spectator";
    }
    
    @Override
    public void sendMessage(String message) {
        System.out.println("[GameLobby] Spectators cannot send messages.");
        return;
    }
}

public class GameLobby {
    List<Player> lobby = new ArrayList<>();

    void registerPlayer(Player player) {
        lobby.add(player);        
    }
    
    void removePlayer(Player player) {
        lobby.remove(player);
    }

    void sendMessage(String message, Player sender) {
        for (Player player : lobby) {
            if (player != sender) {
                player.receiveMessage(message);
            }
        }
    }

    void startMatch() {
        int playercount = 0;
        List<String> human_or_ai = new ArrayList<>();

        for (Player player : lobby) {
            if (!player.getPlayerType().equals("Spectator")) {
                playercount++;
                human_or_ai.add(player.getName());
            }
        }

        if (playercount >= 2) {
            System.out.println("[GameLobby] Starting game with players: " +
            String.join(", ", human_or_ai));
        } else {
            System.out.println("[GameLobby] Not enough players to start a match.");
        }
    }

    public static void main(String[] args) {

    }
}
