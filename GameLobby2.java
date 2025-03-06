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

class AdminPlayer extends AbstractPlayer {
    
    public AdminPlayer(String name, GameLobby lobby) {
        super(name, lobby);
    }

    @Override
    public String getPlayerType() {
        return "AdminPlayer";
    }

    public void kickPlayer(String name) {
        lobby.kickPlayer(name, this);
    }
}

class GameLobby {
    List<Player> players = new ArrayList<>();

    void registerPlayer(Player player) {
        players.add(player);        
    }
    
    void removePlayer(Player player) {
        players.remove(player);
    }
    
    void sendMessage(String message, Player sender) {
        for (Player player : players) {
            if (player != sender) {
                player.receiveMessage(message);
            }
        }
    }

    void startMatch() {
        int playercount = 0;
        List<String> human_or_ai = new ArrayList<>();

        for (Player player : players) {
            if (!player.getPlayerType().equals("Spectator") && !player.getPlayerType().equals("AdminPlayer")) {
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

    void kickPlayer(String name, AdminPlayer admin) {
        Player toremove = null;

        for (Player player : this.players) {
            if (player.getName().equals(name)) {
                toremove = player;
            }
        }
        if (toremove == null) {
            System.out.println("[GameLobby] Player" + name + "not found.");
        }
        else if (!toremove.getPlayerType().equals("AdminPlayer")) { 
            System.out.printf("[GameLobby] Admin %s kicked %s %s from the lobby.\n", admin.name, toremove.getPlayerType(), toremove.getName());
            toremove.leaveGame();
        }
    }
    public static void main(String[] args) {
        
    }
}