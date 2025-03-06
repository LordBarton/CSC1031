import java.util.*;

class CellTower {
    String ID;
    int x, y, radius;

    public CellTower(String ID, int x, int y, int radius) {
        this.ID = ID;
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
}

class Operator {
    String name;
    List<Client> current_clients;
    List<CellTower> usable_towers;

    public Operator(String name) {
        this.name = name;
        this.usable_towers = new ArrayList<>();
        this.current_clients = new ArrayList<>();
    }
}

class Client {
    long phone_number;
    int x, y;
    Operator current_operator;
    CellTower current_tower = null;

    public Client(long phone_number, int x, int y, Operator current_operator) {
        this.phone_number = phone_number;
        this.x = x;
        this.y = y;
        this.current_operator = current_operator;
    }
}

public class NetworkSimulation {
    private static List<Client> clients = new ArrayList<>();
    private static List<Operator> operators = new ArrayList<>();
    private static List<CellTower> towers = new ArrayList<>();

    public static CellTower find_tower(Client client) {
        double minDistance = Double.MAX_VALUE;
        CellTower bestTower = null;

        for (CellTower tower : client.current_operator.usable_towers) {
            double distance = Math.sqrt(Math.pow((client.x - tower.x), 2) + Math.pow((client.y - tower.y), 2));
            if (distance <= tower.radius && distance < minDistance) {
                minDistance = distance;
                bestTower = tower;
            }
        }
        return bestTower;
    }

    public static void MOVE_CLIENT(long phone_number, int new_x, int new_y) {
        for (Client client : clients) {
            if (client.phone_number == phone_number) {
                client.x = new_x;
                client.y = new_y;
                client.current_tower = find_tower(client);
                break;
            }
        }
    }

    public static void CHANGE_OPERATOR(long phone_number, String new_operator_name) {
        for (Client client : clients) {
            if (client.phone_number == phone_number) {
                for (Operator operator : operators) {
                    if (operator.name.equals(new_operator_name)) {
                        client.current_operator.current_clients.remove(client);
                        client.current_operator = operator;
                        operator.current_clients.add(client);
                        client.current_tower = find_tower(client);
                        return;
                    }
                }
            }
        }
    }

    public static void TOWER_CLIENT_COUNT(String tower_ID) {
        int count = 0;
        for (Client client : clients) {
            if (client.current_tower != null && client.current_tower.ID.equals(tower_ID)) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static int OPERATOR_SUBSCRIBER_COUNT(String operator_name) {
        for (Operator operator : operators) {
            if (operator.name.equals(operator_name)) {
                return operator.current_clients.size();
            }
        }
        return 0;
    }

    public static void ADD_CLIENT(long phone_number, String operator_name, int x, int y) {
        for (Operator operator : operators) {
            if (operator.name.equals(operator_name)) {
                Client newClient = new Client(phone_number, x, y, operator);
                newClient.current_tower = find_tower(newClient);
                clients.add(newClient);
                operator.current_clients.add(newClient);
                return;
            }
        }
    }

    public static void REMOVE_CLIENT(long phone_number) {
        for (Operator operator : operators) {
            for (Client client : operator.current_clients) {
                if (client.phone_number == phone_number) {
                    operator.current_clients.remove(client);
                }
            }
        }
        clients.removeIf(client -> client.phone_number == phone_number);
    }

    public static void ADD_TOWER(String ID, int x, int y, int radius) {
        CellTower newTower = new CellTower(ID, x, y, radius);
        towers.add(newTower);
    }

    public static void REGISTER_OPERATOR_TOWER(String operator_name, String tower_ID) {
        for (Operator operator : operators) {
            if (operator.name.equals(operator_name)) {
                for (CellTower tower : towers) {
                    if (tower.ID.equals(tower_ID)) {
                        operator.usable_towers.add(tower);
                        return;
                    }
                }
            }
        }
    }

    public static void REMOVE_TOWER(String tower_ID) {
        towers.removeIf(tower -> tower.ID.equals(tower_ID));
        for (Operator operator : operators) {
            operator.usable_towers.removeIf(tower -> tower.ID.equals(tower_ID));
        }

        for (Client client : clients) {
            if (client.current_tower != null && client.current_tower.ID.equals(tower_ID)) {
                client.current_tower = find_tower(client);
            }
        }
    }

    public static void NO_SIGNAL_COUNT() {
        for (Operator operator : operators) {
            int count = 0;
            for (Client client : operator.current_clients) {
                if (client.current_tower == null) {
                    count++;
                }
            }
            System.out.println(operator.name + ": " + count + " phones without signal.");
        }
    }

    public static void ADD_OPERATOR(String operator_name) {
        operators.add(new Operator(operator_name));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();
            switch (command) {
                case "ADD_OPERATOR":
                    ADD_OPERATOR(scanner.nextLine().trim());
                    break;
                case "ADD_TOWER":
                    ADD_TOWER(scanner.nextLine().trim(), Integer.parseInt(scanner.nextLine().trim()),
                            Integer.parseInt(scanner.nextLine().trim()), Integer.parseInt(scanner.nextLine().trim()));
                    break;
                case "REGISTER_OPERATOR_TOWER":
                    REGISTER_OPERATOR_TOWER(scanner.nextLine().trim(), scanner.nextLine().trim());
                    break;
                case "ADD_CLIENT":
                    ADD_CLIENT(Long.parseLong(scanner.nextLine().trim()), scanner.nextLine().trim(),
                            Integer.parseInt(scanner.nextLine().trim()), Integer.parseInt(scanner.nextLine().trim()));
                    break;
                case "TOWER_CLIENT_COUNT":
                    TOWER_CLIENT_COUNT(scanner.nextLine().trim());
                    break;
                case "MOVE_CLIENT":
                    MOVE_CLIENT(Long.parseLong(scanner.nextLine().trim()), Integer.parseInt(scanner.nextLine().trim()),
                            Integer.parseInt(scanner.nextLine().trim()));
                    break;
                case "CHANGE_OPERATOR":
                    CHANGE_OPERATOR(Long.parseLong(scanner.nextLine().trim()), scanner.nextLine().trim());
                    break;
                case "REMOVE_CLIENT":
                    REMOVE_CLIENT(Long.parseLong(scanner.nextLine().trim()));
                    break;
                case "REMOVE_TOWER":
                    REMOVE_TOWER(scanner.nextLine().trim());
                    break;
                case "OPERATOR_SUBSCRIBER_COUNT":
                    System.out.println(OPERATOR_SUBSCRIBER_COUNT(scanner.nextLine().trim()));
                    break;
                case "NO_SIGNAL_COUNT":
                    NO_SIGNAL_COUNT();
                    break;
                default:
                    System.out.println("Unknown command: " + command);
                    break;
            }
        }
        scanner.close();
    }
}
